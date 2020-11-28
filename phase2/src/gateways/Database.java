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
            createTables();

        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            System.out.println("Something went wrong trying to access the database.");
            e.printStackTrace();
        }
    }

    /**
     * Creates a users table, an events table and a messages table.
     * NOTE: Schema not final!
     */
    private void createTables() throws SQLException {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id blob PRIMARY KEY,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + " friends blob,\n"
                + " conversations blob,\n"
                + " events blob"
                + ");";

        String sqlMsgs = "CREATE TABLE IF NOT EXISTS messages (\n"
                + "	id blob PRIMARY KEY,\n"
                + "	body text NOT NULL,\n"
                + "	timeSent blob NOT NULL,\n"
                + ");";

        String sqlEvts = "CREATE TABLE IF NOT EXISTS events (\n"
                + "	id blob PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity blob NOT NULL,\n"
                + " attendees blob, \n"
                + ");";


        stmt = conn.createStatement();
        stmt.execute(sqlUsers);
        stmt.execute(sqlMsgs);
        stmt.execute(sqlEvts);
    }
}
