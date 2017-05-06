package project;

import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException {
        System.out.println("Started...");

        DbFacade.createDb("persons2");
    }

}
