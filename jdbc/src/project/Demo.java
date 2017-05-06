package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {

    static String baseUrl = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/mysql?useSSL=false";

    public static void main(String[] args) throws SQLException {
        System.out.println("START");

        // 1. Create database and table for our demo
        Connection rawConnection = DriverManager.getConnection(baseUrl, "root", "---");
        Statement statement = rawConnection.createStatement();
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        statement.executeUpdate("create database if not exists persons");
        statement.close();

        rawConnection.commit();
        rawConnection.close();
    }

}
