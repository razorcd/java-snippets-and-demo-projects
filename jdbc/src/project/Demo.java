package project;

import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException {
        System.out.println("Started...");

        DbFacade.createDb("db1");
        DbFacade.createTable("db1", "table1");
    }

}
