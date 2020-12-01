package gateways;

import java.sql.SQLException;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        Database db = new Database();
        try {
            UUID dummyID = UUID.fromString("61c9da20-33fa-11eb-adc1-0242ac120002");

            // Run this method if you have an empty db
            //db.insertUser(dummyID, "test", "test");

            db.getAllUsers();
        } catch (SQLException e) {
            System.out.println("Something went wrong while connecting to the database.");
            e.printStackTrace();
        }
    }
}
