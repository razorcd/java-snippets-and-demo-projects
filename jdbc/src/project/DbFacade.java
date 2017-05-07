package project;

import java.sql.*;
import java.util.List;

public class DbFacade {

    private static String dbUser = "root";
    private static String dbPass = "pass123";
    private static String baseUrl = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/";
    private static String useSsl = "?useSSL=false";

    /**
     * Create database
     *
     * @param dbName
     * @throws SQLException
     */
    public static void createDb(String dbName) throws SQLException{
        Connection rawConnection = DriverManager.getConnection(baseUrl + useSsl, dbUser, dbPass);
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        Statement statement = rawConnection.createStatement();
        statement.executeUpdate("create database if not exists " + dbName);
        statement.close();

        rawConnection.commit();
        rawConnection.close();
    }

    /**
     * Create table
     *
     * @param tableName
     */
    public static void createTable(String dbName, String tableName) throws SQLException {
        Connection rawConnection = DriverManager.getConnection(baseUrl + dbName + useSsl, dbUser, dbPass);
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        Statement statement = rawConnection.createStatement();
        statement.executeUpdate("drop table if exists " + tableName);
        statement.executeUpdate("create table " + tableName +
                " (id_num int not null, first_name varchar(50), last_name varchar(50), primary key (id_num))");
        statement.close();

        rawConnection.commit();
        rawConnection.close();
    }

    public static void insertRows(String dbName, String tableName, List<Row> rows) throws SQLException {
        Connection rawConnection = DriverManager.getConnection(baseUrl + dbName + useSsl, dbUser, dbPass);
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        PreparedStatement ps = rawConnection.prepareStatement("INSERT INTO " + dbName + "." + tableName + " VALUES (?,?,?)");
        for (Row row : rows) {
            ps.setString(1, row.getId_num());
            ps.setString(2, row.getFirstName());
            ps.setString(3, row.getLastNamed());
            ps.executeUpdate();
        }

        rawConnection.commit();
        rawConnection.close();
    }

}
