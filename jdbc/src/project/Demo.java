package project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws SQLException {
        System.out.println("Started...");

        DbFacade.createDb("db1");
        DbFacade.createTable("db1", "table1");

        Row row1 = new Row();
        row1.setId_num("1");
        row1.setFirstName("Aaaa");
        row1.setLastNamed("Bbbb");

        List<Row> rows = new ArrayList<>();
        rows.add(row1);

        DbFacade.insertRows("db1", "table1", rows);

        DbFacade.removeDb("db1");
    }

}
