package database;

import java.io.File;
import java.sql.*;
import java.util.UUID;

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
            createTables();

        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            System.out.println("Something went wrong trying to access the database.");
            e.printStackTrace();
        }
    }

    /**
     * Creates a new row in the <code>users</code> table that contains all user information.
     * @param userID The unique ID for this user.
     * @param username The username for this user.
     * @param password The password for this user.
     * @throws SQLException Thrown when executing the SQL statement goes wrong.
     */
    public void insertUser(UUID userID, String username, String password, String type) throws SQLException {
        String sql = " INSERT INTO users (uuid, username, password, type)"
                + " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, userID.toString());
        ps.setString(2, username);
        ps.setString(3, password);
        ps.setString(4, type);
        ps.execute();
    }

    /**
     * Returns all user data within the database (for now, the method simply prints this data).
     * @throws SQLException Thrown when executing the SQL statement goes wrong.
     */
    public ResultSet getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users;";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public void updateUserType(UUID userID, String newType) throws SQLException {
        String sql = " UPDATE users SET type = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newType);
        ps.setString(2, userID.toString());
        ps.execute();
    }

    public void updateUserPassword(UUID userID, String newPassword) throws SQLException {
        String sql = " UPDATE users SET password = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newPassword);
        ps.setString(2, userID.toString());
        ps.execute();
    }

    /**
     * Creates a users table, an events table and a messages table.
     * These tables are only created if they don't already exist.
     * NOTE: Schema not final!
     */
    private void createTables() throws SQLException {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + "	username text NOT NULL,"
                + "	password text NOT NULL,"
                + " type text NOT NULL"
                + ");";

        /*String sqlMsgs = "CREATE TABLE IF NOT EXISTS messages (\n"
                + "	id blob PRIMARY KEY,\n"
                + "	body text NOT NULL,\n"
                + "	timeSent blob NOT NULL,\n"
                + ");";

        String sqlEvts = "CREATE TABLE IF NOT EXISTS events (\n"
                + "	id blob PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity blob NOT NULL,\n"
                + " attendees blob, \n"
                + ");";*/

        stmt.execute(sqlUsers);
        //stmt.execute(sqlMsgs);
        //stmt.execute(sqlEvts);
    }
}
