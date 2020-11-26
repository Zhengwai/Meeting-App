package gateways;

import java.io.File;
import java.sql.*;

public class Database {
    private Connection conn;
    private Statement stmt;

    public Database() {
        initializeDB();
    }

    /**
     * Attempts to create a connection to the MySQLite database.
     * Creates a database if there already isn't one.
     */
    private void initializeDB() {
        try {
            File file = new File("phase2.db");
            if (!file.exists()) {
                Class.forName("org.sqlite.JDBC").newInstance();
            }

            conn = DriverManager.getConnection("jdbc:sqlite:phase2.db");
            stmt = conn.createStatement();

        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            System.out.println("Something went wrong trying to access the database.");
            e.printStackTrace();
        }
    }
}
