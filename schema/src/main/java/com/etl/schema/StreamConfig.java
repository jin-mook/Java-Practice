package com.etl.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@Slf4j
@Configuration
@EnableKafkaStreams
public class StreamConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public KTable<String, String> kTable(StreamsBuilder builder) {
        // State store for deduplication
        KeyValueBytesStoreSupplier dedupStoreSupplier = Stores.inMemoryKeyValueStore("dedup-store");
        builder.addStateStore(Stores.keyValueStoreBuilder(dedupStoreSupplier, Serdes.String(), Serdes.String()));

        KStream<String, String> stream = builder.stream("mysql-source.mysql-source.Test", Consumed.with(Serdes.String(), Serdes.String()));

        KTable<String, String> myTable = stream
                .selectKey((key, value) -> {
                    JsonNode jsonNode;
                    try {
                        jsonNode = objectMapper.readTree(value);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    JsonNode afterNode = jsonNode.get("payload").get("after");
                    return afterNode.get("UserID").asText();
                })
                .mapValues(value -> {
                    JsonNode jsonNode;
                    try {
                        jsonNode = objectMapper.readTree(value);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    JsonNode afterNode = jsonNode.get("payload").get("after");
                    return afterNode.toString();
                })
                .toTable(Materialized.with(Serdes.String(), Serdes.String()));

        myTable.toStream()
                .transform(() -> new DeduplicationTransformer("dedup-store"), "dedup-store")
                .peek(((key, value) -> log.info("Key: " + key + ", Value: " + value)))
                .to("test-streams-to", Produced.with(Serdes.String(), Serdes.String()));

        return myTable;
    }

    private class DeduplicationTransformer implements org.apache.kafka.streams.kstream.Transformer<String, String, KeyValue<String, String>> {

        private final String storeName;
        private KeyValueStore<String, String> stateStore;

        DeduplicationTransformer(String storeName) {
            this.storeName = storeName;
        }

        @Override
        public void init(org.apache.kafka.streams.processor.ProcessorContext context) {
            this.stateStore = context.getStateStore(storeName);
        }

        @Override
        public KeyValue<String, String> transform(String key, String value) {
            String oldValue = stateStore.get(key);
            if (oldValue == null || !oldValue.equals(value)) {
                stateStore.put(key, value);
                return new KeyValue<>(key, value);
            } else {
                return null; // Skip output if the value has not changed
            }
        }

        @Override
        public void close() {
        }
    }



    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("schema-change.kafka-streams-test");

        stream.map(((key, value) -> {
                    JsonNode rootNode;
                    try {
                        rootNode = objectMapper.readTree(value);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    JsonNode ddlNode = rootNode.path("ddl");
                    String ddlQuery = ddlNode.asText();
                    log.info("ddlQuery = {}", ddlQuery);
                    return KeyValue.pair(key, ddlQuery);
                }))
                .flatMap(((key, value) -> {
                    try {
                        Statement statement = CCJSqlParserUtil.parse(value);

                        if (statement instanceof CreateTable createTable) {
                            // postgresql 쿼리
                            convertAutoIncrementToSerial(createTable);
                            String postgresQuery = createTable.toString();

                            // mssql 쿼리
                            convertAutoIncrementToIdentity(createTable);
                            String mssqlQuery = createTable.toString();
                            return List.of(KeyValue.pair("postgres", postgresQuery), KeyValue.pair("mssql", mssqlQuery));
                        }
                        return List.of(KeyValue.pair("mysql", value));
                    } catch (JSQLParserException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .to("test-streams-to");
        return stream;
    }

    private void convertAutoIncrementToSerial(CreateTable createTable) {
        List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
        for (ColumnDefinition columnDefinition : columnDefinitions) {
            if (columnDefinition.getColumnSpecs() != null &&
                    columnDefinition.getColumnSpecs().contains("AUTO_INCREMENT")) {
                columnDefinition.getColumnSpecs().remove("AUTO_INCREMENT");
                columnDefinition.setColDataType(new ColDataType("SERIAL"));
            }
        }
    }

    private void convertAutoIncrementToIdentity(CreateTable createTable) {
        List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
        for (ColumnDefinition columnDefinition : columnDefinitions) {
            if (columnDefinition.getColumnSpecs() != null &&
                    columnDefinition.getColumnSpecs().contains("AUTO_INCREMENT")) {
                columnDefinition.getColumnSpecs().remove("AUTO_INCREMENT");
                columnDefinition.getColumnSpecs().add("IDENTITY(1,1)");
            }
        }
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration defaultKafkaStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "test-streams-Test");
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }
}
