package database;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO: Needs to be refactored after all functionality implemented (Facade?)
public class Database {
    private Connection conn;
    private Statement stmt;

    public Database() {

        // For first time running
        if (! new File("phase2.db").exists()) {
            initializeDB();
            return;
        }

        // Opens connection to DB
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:phase2.db");
            stmt = conn.createStatement();
        } catch (IllegalAccessException | SQLException | InstantiationException | ClassNotFoundException e) {
            System.out.println("Something went wrong while trying to connect to the database.");
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
    protected void insertUser(UUID userID, String username, String password, String type) throws SQLException {
        String sql = " INSERT INTO users (uuid, username, password, type)"
                + " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, userID.toString());
        ps.setString(2, username);
        ps.setString(3, password);
        ps.setString(4, type);
        ps.execute();
    }

    protected void insertNewMessage(UUID messageID, String body, UUID senderID,  String timeSent) throws SQLException {
        String sql = " INSERT INTO messages (uuid, body, senderID, timeSent)"
                + " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, messageID.toString());
        ps.setString(2, body);
        ps.setString(3, senderID.toString());
        ps.setString(4, timeSent);
        ps.execute();
    }

    protected void insertNewConversation(UUID conID, ArrayList<UUID> members, String name, int readOnly, UUID owner) throws SQLException {
        String sql = " INSERT INTO conversations (uuid, members, convName, readonly, owner)"
                + " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, conID.toString());
        ps.setObject(2, members);
        ps.setString(3, name);
        ps.setInt(4, readOnly);
        if (owner != null) ps.setString(5, owner.toString());
        ps.execute();
    }

    protected void insertNewEvent(UUID eventID, String name, String desc, String startTime, String endTime, int capacity, UUID roomID, String type, Boolean isVIP) throws SQLException {
        String sql = " INSERT INTO events (uuid, name, description, startTime, endTime, capacity, room, type, isVIP)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, eventID.toString());
        ps.setString(2, name);
        ps.setString(3, desc);
        ps.setString(4, startTime);
        ps.setString(5, endTime);
        ps.setInt(6, capacity);
        ps.setString(7, roomID.toString());
        ps.setString(8, type);
        ps.setInt(9, isVIP ? 1 : 0);
        ps.execute();
    }

    protected void insertRoom(UUID roomID, String name, int capacity) throws SQLException {
        String sql = "INSERT INTO rooms (uuid, name, capacity)" +
                "VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, roomID.toString());
        ps.setString(2, name);
        ps.setInt(3, capacity);
        ps.execute();
    }

    protected void insertRequest(UUID requestingUser, String text, ArrayList<String> tags, Boolean resolved) throws SQLException {
        String sql = " INSERT INTO requests (uuid, requestText, tags, resolved)" +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, requestingUser.toString());
        ps.setString(2, text);
        ps.setObject(3, tags);
        ps.setInt(4, resolved ? 1 : 0);
        ps.execute();
    }


    protected ResultSet getAllFromTable(String tableName) throws SQLException{
        String sql = "SELECT * FROM " + tableName + ";";
        return stmt.executeQuery(sql);
    }

    protected void updateTableRowValue(String table, String columnName, UUID idFilter, String newValue) throws SQLException {
        String sql = " UPDATE " + table + " SET " + columnName + " = ? WHERE uuid = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newValue);
        ps.setString(2, idFilter.toString());
        ps.execute();
    }

    protected void updateTableRowValue(String table, String columnName, UUID idFilter, ArrayList<UUID> newValue) throws SQLException {
        String sql = " UPDATE " + table + " SET " + columnName + " = ? WHERE uuid = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, newValue);
        ps.setString(2, idFilter.toString());
        ps.execute();
    }

    protected void updateTableRowValueStrings(String table, String columnName, UUID idFilter, ArrayList<String> newValue) throws SQLException {
        String sql = " UPDATE " + table + " SET " + columnName + " = ? WHERE uuid = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, newValue);
        ps.setString(2, idFilter.toString());
        ps.execute();
    }

    protected void updateTableRowValue(String table, String columnName, UUID idFilter, int newValue) throws SQLException {
        String sql = " UPDATE " + table + " SET " + columnName + " = ? WHERE uuid = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, newValue);
        ps.setString(2, idFilter.toString());
        ps.execute();
    }

    protected void deleteAnEvent(UUID eventID) throws SQLException {
        String sql = "DELETE FROM events WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, eventID.toString());
        ps.execute();
    }

    /**
     * Attempts to create a connection to the MySQLite database.
     * Creates a database if there already isn't one.
     */
    private void initializeDB() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:phase2.db");
            stmt = conn.createStatement();
            createTables();
            // For testing purposes.
            UUID adminID = UUID.fromString("37ce95a7-b11f-4bc1-938e-4ab8b5b5d225");
            insertUser(adminID, "DBAdmin", "DBAdmin", "o");
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            System.out.println("Something went wrong on first time initialization of DB.");
            e.printStackTrace();
        }
    }

    /**
     * Creates tables for:
     * users,   messages,   conversations,   events,   rooms,   requests
     * These tables are only created if they don't already exist.
     * NOTE: Schema not final!
     */
    private void createTables() throws SQLException {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + "	username text NOT NULL,"
                + "	password text NOT NULL,"
                + " type text NOT NULL,"
                + " friends object,"
                + " events object"
                + ");";

        String sqlMsgs = "CREATE TABLE IF NOT EXISTS messages ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + " body text NOT NULL,"
                + " senderID text NOT NULL,"
                + "	timeSent text NOT NULL"
                + ");";

        String sqlConvos = "CREATE TABLE IF NOT EXISTS conversations ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + " members object NOT NULL,"
                + " messages object,"
                + " convName text,"
                + " readonly TINYINT NOT NULL,"
                + " owner text,"
                + " archivedFor object,"
                + " unreadFor object"
                + ");";

        String sqlEvts = "CREATE TABLE IF NOT EXISTS events ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + "	name text NOT NULL,"
                + " description text,"
                + " startTime text,"
                + " endTime text,"
                + "	capacity INTEGER NOT NULL,"
                + " attendees object,"
                + " room text NOT NULL,"
                + " type text NOT NULL,"
                + " speakers object,"
                + " isVIP TINYINT NOT NULL"
                + ");";

        String sqlRooms = " CREATE TABLE IF NOT EXISTS rooms ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + " name text NOT NULL,"
                + " capacity INTEGER NOT NULL,"
                + " events object"
                + ");";

        String sqlRequests = " CREATE TABLE IF NOT EXISTS requests ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + " requestText text NOT NULL,"
                + " tags object,"
                + " resolved TINYINT NOT NULL"
                + ");";

        stmt.execute(sqlUsers);
        stmt.execute(sqlMsgs);
        stmt.execute(sqlConvos);
        stmt.execute(sqlEvts);
        stmt.execute(sqlRooms);
        stmt.execute(sqlRequests);
    }
}