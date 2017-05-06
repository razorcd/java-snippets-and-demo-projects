package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbFacade {

    private static String dbUser = "root";
    private static String dbPass = "---";
    private static String baseUrl = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/mysql?useSSL=false";

    /**
     * Create database
     *
     * @param dbName
     * @throws SQLException
     */
    public static void createDb(String dbName) throws SQLException{
        Connection rawConnection = DriverManager.getConnection(baseUrl, dbUser, dbPass);
        Statement statement = rawConnection.createStatement();
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        statement.executeUpdate("create database if not exists " + dbName);
        statement.close();

        rawConnection.commit();
        rawConnection.close();
    }

    public static void createTable(String tableName) {

    }

}
