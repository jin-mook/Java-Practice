package com.hello.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.management.MXBean;
import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration extends DefaultBatchConfiguration {

//    @Bean
//    public DataSource batchDataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//                .addScript("/org/springframework/batch/core/schema-h2.sql")
//                .generateUniqueName(true).build();
//    }
//
//    @Bean
//    public JdbcTransactionManager batchTransactionManager(DataSource dataSource) {
//        return new JdbcTransactionManager(dataSource);
//    }
//
    @Bean
    public Job simpleJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step simpleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("simpleStep", jobRepository)
                .tasklet(((contribution, chunkContext) -> {
                    log.info(">>>>>> this is step1");
                    return RepeatStatus.FINISHED;
                }), transactionManager)
                .build();
    }

//    @Bean
//    public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
//        return new JobBuilder("sampleJob", jobRepository)
//                .start(sampleStep)
//                .build();
//    }
//
//    @Bean
//    public Step sampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("sampleStep", jobRepository)
//                .<String, String>chunk(10, transactionManager)
//                .reader(() -> {
//                    log.info(">>>>>> reader start <<<<<<");
//                    return "test";
//                })
//                .writer(chunk -> log.info("chunk = {}", chunk.getItems()))
//                .build();
//    }

//    @Bean
//    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
//        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
//        jobLauncher.setJobRepository(jobRepository);
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }

}












