package project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws SQLException {
        System.out.println("Start.");

        //create DB and Table
        DbFacade.createDb("db1");
        DbFacade.createTable("db1", "table1");

        //insert data in DB
        List<Row> validRows = generateValidRowList();
        DbFacade.insertRowsWithoutTransaction("db1", "table1", validRows);

        //insert data in DB using a Transaction
        List<Row> invalidRows = generateInvalidRowList();
        DbFacade.insertRowsWithTransaction("db1", "table1", invalidRows);

        //list data from DB
        List<Row> dbRows = DbFacade.listRows("db1", "table1");
        System.out.println("Listing data from database db1 and table table1:");
        System.out.println("id_num   first_name   last_name");
        for (Row row : dbRows) {
            System.out.println(row.getIdNum() + "   " + row.getFirstName() + "   " + row.getLastName());
        }

        //remove DB
        DbFacade.removeDb("db1");

        System.out.println("End.");
    }

    private static List<Row> generateValidRowList() {
        Row row1 = new Row();
        row1.setIdNum("1");
        row1.setFirstName("Aaaa1");
        row1.setLastName("Bbbb1");

        Row row2 = new Row();
        row2.setIdNum("22");
        row2.setFirstName("Aaaa2");
        row2.setLastName("Bbbb2");

        List<Row> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        return rows;
    }

    private static List<Row> generateInvalidRowList() {
        Row row1 = new Row();
        row1.setIdNum("5");
        row1.setFirstName("Record should not be in DB because of transaction");
        row1.setLastName("Dummy record");

        Row row2 = new Row();
        row2.setIdNum("99");
        row2.setFirstName(null);
        row2.setLastName(null);

        List<Row> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        return rows;
    }
}
