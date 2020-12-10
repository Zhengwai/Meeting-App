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

    /**
     * Returns all user data within the database (for now, the method simply prints this data).
     * @throws SQLException Thrown when executing the SQL statement goes wrong.
     */
    protected ResultSet getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users;";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    protected void updateUserType(UUID userID, String newType) throws SQLException {
        String sql = " UPDATE users SET type = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newType);
        ps.setString(2, userID.toString());
        ps.execute();
    }

    protected void updateUserPassword(UUID userID, String newPassword) throws SQLException {
        String sql = " UPDATE users SET password = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newPassword);
        ps.setString(2, userID.toString());
        ps.execute();
    }

    protected void updateUserEvents(UUID userID, ArrayList<UUID> newEvents) throws SQLException {
        String sql = " UPDATE users SET events = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, newEvents); // Not preferable for a relational database but for now it works.
        ps.setString(2, userID.toString());
        ps.execute();
    }

    protected void updateUserFriends(UUID userID, ArrayList<UUID> newFriends) throws SQLException {
        String sql = " UPDATE users SET friends = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, newFriends);
        ps.setString(2, userID.toString());
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

    protected ResultSet getAllMessages() throws SQLException {
        String sql = "SELECT * FROM messages;";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    protected void insertNewConversation(UUID conID, ArrayList<UUID> members, String name, int readOnly, UUID owner) throws SQLException {
        String sql = " INSERT INTO conversations (conID, members, convName, readonly, owner, unreadMessages)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, conID.toString());
        ps.setObject(2, members);
        ps.setString(3, name);
        ps.setInt(4, readOnly);
        ps.setString(5, owner.toString());
    }

    protected ResultSet getAllConversations() throws SQLException {
        String sql = "SELECT * FROM conversations;";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    protected void updateConversationMembers(UUID conID, ArrayList<UUID> newMembers) throws SQLException {
        String sql = " UPDATE messages SET members = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, newMembers);
        ps.setString(2, conID.toString());
        ps.execute();
    }

    protected void updateConversationName(UUID conID, String newName) throws SQLException {
        String sql = " UPDATE messages SET name = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newName);
        ps.setString(2, conID.toString());
        ps.execute();
    }

    protected void updateConversationReadOnly(UUID conID, boolean readOnly) throws SQLException {
        String sql = " UPDATE messages SET readOnly  = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, readOnly ? 1 : 0);
        ps.setString(2, conID.toString());
        ps.execute();
    }

    protected void updateConversationOwner(UUID conID, UUID newOwnerID) throws SQLException {
        String sql = " UPDATE messages SET members = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newOwnerID.toString());
        ps.setString(2, conID.toString());
        ps.execute();
    }

    protected void insertNewEvent(UUID eventID, String name, String desc, Date startTime, Date endTime, int capacity) throws SQLException {
        String sql = " INSERT INTO events (uuid, name, capacity)"
                + "VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, eventID.toString());
        ps.setString(2, name);
        ps.setString(3, desc);
        ps.setDate(4, startTime);
        ps.setDate(5, endTime);
        ps.setInt(6, capacity);
        ps.execute();
    }

    protected void updateEventName(UUID eventID, String newName) throws SQLException {
        String sql = " UPDATE events SET name  = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, newName);
        ps.setString(2, eventID.toString());
        ps.execute();
    }

    protected void updateEventCapacity(UUID eventID, int newCapacity) throws SQLException {
        String sql = " UPDATE events SET capacity  = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, newCapacity);
        ps.setString(2, eventID.toString());
        ps.execute();
    }

    protected void updateEventAttendees(UUID eventID, List<UUID> newAttendees) throws SQLException {
        String sql = " UPDATE events SET attendees  = ? WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, newAttendees);
        ps.setString(2, eventID.toString());
        ps.execute();
    }

    protected void updateEventTime(UUID eventID, Date newStartTime, Date newEndTime) throws SQLDataException {
        String sql = " UPDATE events SET ";
    }

    protected ResultSet getAllEvents() throws SQLException {
        String sql = "SELECT * FROM events;";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
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
                + " convName text,"
                + " readonly TINYINT NOT NULL,"
                + " owner text,"
                + " unreadMessages object"
                + ");";

        String sqlEvts = "CREATE TABLE IF NOT EXISTS events ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " uuid text NOT NULL,"
                + "	name text NOT NULL,"
                + " description text,"
                + " startTime date,"
                + " endTime date,"
                + "	capacity INTEGER NOT NULL,"
                + " attendees object"
                + ");";

        stmt.execute(sqlUsers);
        stmt.execute(sqlMsgs);
        stmt.execute(sqlConvos);
        stmt.execute(sqlEvts);
    }
}
