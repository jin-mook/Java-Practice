package com.company.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverManagerTest {

    public static void main(String[] args) {
        DriverManager.drivers().forEach(driver -> System.out.println("driver = " + driver.getClass()));

        try (Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc?user=root&password=password");
             Connection postgresConnection = DriverManager.getConnection("jdbc:postgresql://localhost/jdbc?user=root&password=password");
             Statement mysqlConnectionStatement = mysqlConnection.createStatement();
             Statement postgresConnectionStatement = postgresConnection.createStatement()
        ) {
            System.out.println("mysql connection = " + mysqlConnection.getClass().getName());
            System.out.println("postgresql connection = " + postgresConnection.getClass().getName());

            mysqlConnection.setAutoCommit(false);
            mysqlConnectionStatement.execute("insert into connection(name) values ('test1')");
            mysqlConnection.commit();

            postgresConnection.setAutoCommit(false);
            postgresConnectionStatement.execute("insert into connection(name) values ('test1')");
            postgresConnection.rollback();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
