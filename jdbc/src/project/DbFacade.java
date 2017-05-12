package project;

import java.sql.*;
import java.util.ArrayList;
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
                " (id_num int not null, first_name varchar(50) not null, last_name varchar(50) not null, primary key (id_num))");
        statement.close();

        rawConnection.commit();
        rawConnection.close();
    }

    /**
     * Insert rows into table without using a transaction
     *
     * @param dbName
     * @param tableName
     * @param rows
     * @throws SQLException
     */
    public static void insertRowsWithoutTransaction(String dbName, String tableName, List<Row> rows) throws SQLException {
        Connection rawConnection = DriverManager.getConnection(baseUrl + dbName + useSsl, dbUser, dbPass);

        PreparedStatement ps = rawConnection.prepareStatement("INSERT INTO " + dbName + "." + tableName + " VALUES (?,?,?)");
        for (Row row : rows) {
            ps.setString(1, row.getIdNum());
            ps.setString(2, row.getFirstName());
            ps.setString(3, row.getLastName());
            ps.executeUpdate();
        }

        rawConnection.close();
    }

    /**
     * Insert rows into table while using a transaction.
     *
     * @param dbName
     * @param tableName
     * @param rows
     * @throws SQLException
     */
    public static void insertRowsWithTransaction(String dbName, String tableName, List<Row> rows) throws SQLException {
        Connection rawConnection = DriverManager.getConnection(baseUrl + dbName + useSsl, dbUser, dbPass);
        rawConnection.setAutoCommit(false); //we are committing everything explicitly. Enables Transaction.

        try {
            System.out.println("\nStarting updating with transaction process.");
            PreparedStatement ps = rawConnection.prepareStatement("INSERT INTO " + dbName + "." + tableName + " VALUES (?,?,?)");
            for (Row row : rows) {
                ps.setString(1, row.getIdNum());
                ps.setString(2, row.getFirstName());
                ps.setString(3, row.getLastName());
                System.out.println("Executing update for record: " + row.getIdNum());
                ps.executeUpdate();
                System.out.println("Finished update for record: " + row.getIdNum());
            }

            System.out.println("Commiting data to DB.");
            rawConnection.commit();
            System.out.println("Commit is unexpectedly successfull.");
        } catch (SQLException ex) {
            System.out.println("! Committing to DB failed. Error: " + ex.getMessage());
//            throw ex; //finally block is executed before throwing this exception
//            ex.printStackTrace(); //normally trow an app exception here
        } finally {
            System.out.println("Closing db connection.\n");
            rawConnection.close();
        }
    }

    /**
     * List rows from table
     *
     * @param dbName
     * @param tableName
     * @throws SQLException
     */
    public static List<Row> listRows(String dbName, String tableName) throws SQLException {
        List<Row> rows = new ArrayList<>();

        Connection rawConnection = DriverManager.getConnection(baseUrl + dbName + useSsl, dbUser, dbPass);
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        Statement statement = rawConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from " + tableName);
        while (resultSet.next()) {
            Row row = new Row();
            row.setIdNum(resultSet.getString("id_num"));
            row.setFirstName(resultSet.getString("first_name"));
            row.setLastName(resultSet.getString("last_name"));
            rows.add(row);
        }
        statement.close();

        rawConnection.commit();
        rawConnection.close();

        return rows;
    }


    /**
     * Remove database
     *
     * @param dbName
     * @throws SQLException
     */
    public static void removeDb(String dbName) throws SQLException {
        Connection rawConnection = DriverManager.getConnection(baseUrl + dbName + useSsl, dbUser, dbPass);
        rawConnection.setAutoCommit(false); //we are committing everything explicitly

        Statement statement = rawConnection.createStatement();
        statement.executeUpdate("drop database " + dbName);
        statement.close();

        rawConnection.commit();
        rawConnection.close();
    }

}
