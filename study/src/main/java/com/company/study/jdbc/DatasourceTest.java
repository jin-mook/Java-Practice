package com.company.study.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatasourceTest {

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/jdbc?user=root&password=password");

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);

            statement.execute("insert into connection(name) values ('test3')");
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
