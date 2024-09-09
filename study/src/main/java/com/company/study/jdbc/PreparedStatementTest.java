package com.company.study.jdbc;

import java.sql.*;

public class PreparedStatementTest {

    public static void main(String[] args) {
        PreparedStatement test1 = null;
        PreparedStatement test2 = null;

        try (Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc?user=root&password=password&useServerPrepStmts=true&cachePrepStmts=true")
        ) {
            String sql = "select * from connection where id = ?";
            for (int i = 0; i < 2; i++) {
                PreparedStatement preparedStatement = mysqlConnection.prepareStatement(sql);
                preparedStatement.setInt(1, i);

                if (i == 0) {
                    test1 = preparedStatement;
                } else {
                    test2 = preparedStatement;
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("resultSet = " + resultSet);
                // close 메서드를 호출해야 cache 를 진행함
                preparedStatement.close();
            }

            System.out.println(test1 == test2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
