package project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws SQLException {
        System.out.println("Start.");

        DbFacade.createDb("db1");
        DbFacade.createTable("db1", "table1");

        List<Row> rows = generateRowList();
        DbFacade.insertRows("db1", "table1", rows);

        List<Row> dbRows = DbFacade.listRows("db1", "table1");
        System.out.println("Listing data from database db1 and table table1:");
        System.out.println("id_num   first_name   last_name");
        for (Row row : dbRows) {
            System.out.println(row.getId_num() + "   " + row.getFirstName() + "   " + row.getLastNamed());
        }

        DbFacade.removeDb("db1");

        System.out.println("End.");
    }

    private static List<Row> generateRowList() {
        Row row1 = new Row();
        row1.setId_num("1");
        row1.setFirstName("Aaaa1");
        row1.setLastNamed("Bbbb1");

        Row row2 = new Row();
        row2.setId_num("22");
        row2.setFirstName("Aaaa2");
        row2.setLastNamed("Bbbb2");

        List<Row> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        return rows;
    }

}
