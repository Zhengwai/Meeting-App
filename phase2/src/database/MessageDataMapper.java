package database;

import entities.Conversation;
import entities.Message;
import gateways.MessageDataGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

public class MessageDataMapper implements MessageDataGateway  {
    private Database db;

    public MessageDataMapper() {
        db = new Database();
    }

    @Override
    public void insertNewMessage(Message msg) {
        try {
            db.insertNewMessage(msg.getMessageID(), msg.getBody(), msg.getSenderID(), msg.getTimeSent().toString());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert a new message.");
            e.printStackTrace();
        }
    }

    @Override
    public void insertConversation(Conversation c) {
        try {
            db.insertNewConversation(c.getID(), c.getMembers(), c.getName(), c.getReadOnly() ? 1 : 0, c.getOwner());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert a new conversation.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Message> fetchMessages() {
        try {
            ResultSet rs = db.getAllFromTable("messages");
            ArrayList<Message> out = new ArrayList<>();
            while(rs.next()) {
                Message msg = new Message(UUID.fromString(rs.getString("senderID")), rs.getString("body"));
                msg.setMessageID(UUID.fromString(rs.getString("uuid")));
                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                msg.setTimeSent(parser.parse(rs.getString("timeSent")));
                out.add(msg);
            }
            return out;
        } catch (SQLException | ParseException e) {
            System.out.println("Something went wrong trying to get all messages.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void updateConversationMsgIDs(Conversation c) {
        try {
            db.updateTableRowValue("conversations", "messages", c.getID(), c.getMessageIDs());
        } catch (SQLException e) {
            System.out.println("Something went wrong updating this conversation's messages.");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Conversation> fetchConversations() {
        try {
            ResultSet rs = db.getAllFromTable("conversations");
            ArrayList<Conversation> out = new ArrayList<>();
            while (rs.next()) {
                // Data retrieval
                Conversation c = new Conversation();
                UUID conID = UUID.fromString(rs.getString("uuid"));
                c.setConID(conID);
                String rawMembers = (String) rs.getObject("members");


                String rawUnreadMsgs = (String) rs.getObject("unreadFor");
                String rawArchivedFor = (String) rs.getObject("archivedFor");
                String rawMessages = (String) rs.getObject("messages");
                String convName = rs.getString("convName");
                String strOwnerID = rs.getString("owner");


                // Putting the stored data into an instance of conversation
                if (rawMembers != null && !rawMembers.equals("[]")) {
                    rawMembers = rawMembers.substring(1, rawMembers.length() - 1); // Remove the "[" and "]" from string
                    String[] membersList = rawMembers.split(", ");
                    for (String s: membersList) {
                        c.addMember(UUID.fromString(s));
                    }
                }

                if (rawArchivedFor != null && !rawArchivedFor.equals("[]")) {
                    rawArchivedFor = rawArchivedFor.substring(1, rawArchivedFor.length() - 1);
                    String[] archivedForList = rawArchivedFor.split(", ");
                    for (String s: archivedForList) {
                        c.setArchivedFor(UUID.fromString(s));
                    }
                }

                if (rawUnreadMsgs != null && !rawUnreadMsgs.equals("[]")) {
                    rawUnreadMsgs = rawUnreadMsgs.substring(1, rawUnreadMsgs.length() - 1);
                    String[] unreadMsgsList = rawUnreadMsgs.split(", ");
                    for (String s: unreadMsgsList) {
                        c.setUnreadFor(UUID.fromString(s));
                    }
                }

                if (rawMessages != null && !rawMessages.equals("[]")) {
                    rawMessages= rawMessages.substring(1, rawMessages.length() - 1);
                    String[] messagesList = rawMessages.split(", ");
                    for (String s: messagesList) {
                        c.addMessageID(UUID.fromString(s));
                    }
                }


                c.setName(convName);

                if (strOwnerID != null) {
                    c.setOwner(UUID.fromString(strOwnerID));
                }

                // Instance of conversation with all fields retrieved from DB
                out.add(c);
            }

            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to get all conversations.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void updateConversationOwner(Conversation c) {
        try {
            db.updateTableRowValue("conversations", "owner", c.getID(), c.getOwner().toString());
        } catch(SQLException e) {
            System.out.println("Something went wrong try to update this conversation's owner.");
            e.printStackTrace();
        }
    }

    public void updateConversationName(Conversation c) {
        try {
            db.updateTableRowValue("conversations", "convName", c.getID(), c.getName());
        } catch(SQLException e) {
            System.out.println("Something went wrong try to update this conversation's name.");
            e.printStackTrace();
        }
    }

    public void updateConversationMembers(Conversation c) {
        try {
            db.updateTableRowValue("conversations", "members", c.getID(), c.getMembers());
        } catch(SQLException e) {
            System.out.println("Something went wrong try to update this conversation's members.");
            e.printStackTrace();
        }
    }

    public void updateConversationReadOnly(Conversation c) {
        try {
            int val = 0;
            if (c.getReadOnly()) val = 1;

            db.updateTableRowValue("conversations", "readOnly", c.getID(), val);
        } catch(SQLException e) {
            System.out.println("Something went wrong try to update this conversation's readOnly state.");
            e.printStackTrace();
        }
    }

    public void updateConversationArchivedFor(Conversation c) {
        try {
            db.updateTableRowValue("conversations", "messages", c.getID(), c.getArchivedForList());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this conversation's archivedFor list.");
            e.printStackTrace();
        }
    }

    public void updateConversationUnreadFor(Conversation c) {
        try {
            db.updateTableRowValue("conversations", "messages", c.getID(), c.getUnreadForList());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update this conversation's unreadFor list.");
            e.printStackTrace();
        }
    }
}
