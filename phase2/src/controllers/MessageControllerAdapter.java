package controllers;

import controllers.actions.*;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MessageControllerAdapter {
    private ConversationManager cm;
    private UserManager um;
    private UUID userID;
    private EventManager em;
    private HashMap<UUID, ArrayList<UUID>> justReadMessages = new HashMap<>();

    public MessageControllerAdapter(UUID userID, ConversationManager cm, UserManager um, EventManager em) {
        this.cm = cm;
        this.um = um;
        this.userID = userID;
        this.em = em;
    }

    /*
     * TODO: Below are the methods that are to be used before a conversation is selected.
     */



    /**
     * Returns all the messages in a conversation. Each message is an String array of length 3. Term 1 is the name ('Me'
     * the sender is userID, Term 2 is the time sent. Term 3 is the message body. Must use this method for getting a conversation.
     * @param conID UUID of conversation
     * @return Returns an arraylist of String arrays
     */
    public ArrayList<String[]> getMessagesInConversation(UUID conID) {
        ArrayList<String[]> msgs = cm.getMessagesInConversation(conID);
        ArrayList<String[]> newMsgs = new ArrayList<>();
        for (String[] c : msgs) {
            String[] term = new String[3];
            if (UUID.fromString(c[0]).equals(userID)) {
                term[0] = String.valueOf(userID);
            }
            else {
                term[0] = String.valueOf(um.getUserByID(UUID.fromString(c[0])).getUsername());
            }
            term[1] = String.valueOf(c[1]);
            term[2] = String.valueOf(c[2]);
            newMsgs.add(term);
        }
        ArrayList<UUID> unread = new ArrayList<>(cm.getUserUnreadMessages(conID, userID));
        cm.setReadMessage(conID, userID);

        if (!this.justReadMessages.containsKey(conID)) {
            this.justReadMessages.put(conID, unread);
        } else {
            this.justReadMessages.replace(conID, unread);
        }

        return newMsgs;
    }


    /**
     * Adds friend with UUID friendID as friend if that person exists and is not already a friend
     * @param friendID UUID of new friend.
     */
    public void AddFriend(UUID friendID) {
        AddFriendAction addFriend = new AddFriendAction(userID, um, cm, friendID);
        addFriend.run();
    }

    /**
     * Given the UUID of a conversation which has not been archived yet, this archives given covnersation for user.
     * @param conID UUID of conversation
     */
    public void ArchiveConversation(UUID conID) {
        ArchiveConversationAction archiveConversation = new ArchiveConversationAction(userID, um, cm, conID);
        archiveConversation.run();
    }

    /**
     * Returns all non-archived conversation that the user has in an ArrayList of String Lists. Each String list has 2 terms.
     * Term 1 is the name of the conversation. Term 2 is the UUID of the conversation.
     * @return Arraylist of String lists.
     */
    public ArrayList<String[]> getConversations() {
        ArrayList<UUID> ids = cm.getUserConversationsNotArchived(userID);
        ArrayList<String[]> ret = new ArrayList<>();

        for (UUID id: ids) {
            String name;
            if (cm.noNameExists(id)) {
                if (cm.getMemberIDsInConversation(id).get(0).equals(userID)) {
                    name = um.getUserByID(cm.getMemberIDsInConversation(id).get(1)).getUsername();
                } else {
                    name = um.getUserByID(cm.getMemberIDsInConversation(id).get(0)).getUsername();
                }
            } else {
                name = cm.getName(id);
            }
            String[] s = new String[2];
            s[0] = name;
            s[1] = String.valueOf(id);
            ret.add(s);
        }

        return ret;
    }

    /**
     * Returns all archived conversation that the user has in an ArrayList of String Lists. Each String list has 2 terms.
     * Term 1 is the name of the conversation. Term 2 is the UUID of the conversation.
     * @return Arraylist of String lists.
     */
    public ArrayList<String[]> getArchivedConversations() {
        ArrayList<UUID> ids = cm.getUserConversationsArchived(userID);
        ArrayList<String[]> ret = new ArrayList<>();

        for (UUID id: ids) {
            String name;
            if (cm.noNameExists(id)) {
                if (cm.getMemberIDsInConversation(id).get(0).equals(userID)) {
                    name = um.getUserByID(cm.getMemberIDsInConversation(id).get(1)).getUsername();
                } else {
                    name = um.getUserByID(cm.getMemberIDsInConversation(id).get(0)).getUsername();
                }
            } else {
                name = cm.getName(id);
            }
            String[] s = new String[2];
            s[0] = name;
            s[1] = String.valueOf(id);
            ret.add(s);
        }

        return ret;
    }

    /**
     * Returns all unread conversation that the user has in an ArrayList of String Lists. Each String list has 2 terms.
     * Term 1 is the name of the conversation. Term 2 is the UUID of the conversation.
     * @return Arraylist of String lists.
     */
    public ArrayList<String[]> getUnreadConversations() {
        ArrayList<UUID> ids = cm.getUserConversationsUnread(userID);
        ArrayList<String[]> ret = new ArrayList<>();

        for (UUID id: ids) {
            String name;
            if (cm.noNameExists(id)) {
                if (cm.getMemberIDsInConversation(id).get(0).equals(userID)) {
                    name = um.getUserByID(cm.getMemberIDsInConversation(id).get(1)).getUsername();
                } else {
                    name = um.getUserByID(cm.getMemberIDsInConversation(id).get(0)).getUsername();
                }
            } else {
                name = cm.getName(id);
            }
            String[] s = new String[2];
            s[0] = name;
            s[1] = String.valueOf(id);
            ret.add(s);
        }

        return ret;
    }

    /**
     * Only Implemented for Organizers. Used to Message All Attendees with a message with body as given body, in a
     * read-only conversation with given title.
     * @param body Body of the Message.
     * @param title Title of the Message.
     */
    public void MessageAllAttendees(String body, String title) {
        MessageAllAttendeesAction messageAll = new MessageAllAttendeesAction(userID, um, cm, body, title);
        messageAll.run();
    }

    /**
     * Only Implemented for Organizers. Used to Message All Speakers with a message with body as given body, in a
     * read-only conversation with given title.
     * @param body Body of the Message.
     * @param title Title of the Message.
     */
    public void MessageAllSpeakers(String body, String title) {
        MessageAllSpeakersAction messageAll = new MessageAllSpeakersAction(userID, um, cm, body, title);
        messageAll.run();
    }

    /**
     * Only implemented for Speakers. Used to message all attendees of the event with id eventID. Assumes that userID is
     * the speaker for event with id eventID.
     * @param body body of the message
     * @param title title of the message
     * @param eventID UUID of the event to message attendees of
     */
    public void MessageAllEventAttendees(String body, String title, UUID eventID) {
        MessageAllEventAttendeesAction messageAll = new MessageAllEventAttendeesAction(userID, um, cm, em, body, title, eventID);
        messageAll.run();
    }



    /*
     * TODO: Below are actions that are to be done once a Conversation is selected
     */



    /**
     * Delete message by giving the UUID of msg and UUID of conversation containing msg
     * @param msgID UUID of msg
     * @param conID UUID of conversation
     */
    public void DeleteMessage(UUID msgID, UUID conID) {
        cm.deleteMessage(conID, msgID);
    }

    /**
     * Returns an arraylist of Lists of Strings which corresponds to the messages the user just read this session.
     * @param conID UUID of conversation
     * @return Arraylist of list of Strings corresponding to messages
     */
    public ArrayList<String[]> GetUnreadMessages(UUID conID) {
        ArrayList<UUID> msgs = this.justReadMessages.get(conID);
        return cm.getMessagesInList(msgs);
    }

    /**
     * Marks message with id msgID as unread for user with id userID in conversation with id conID
     * @param msgID UUID of message
     * @param conID UUID of conversation
     */
    public void MarkUnread(UUID msgID, UUID conID) {
        cm.setUnreadMessage(conID, userID, msgID);
    }

    /**
     * Method for messaging the conversation with id conID from user with id userID, and message body of body
     * @param conID UUID of conversation
     * @param body Message body
     */
    public void MessageConversation(UUID conID, String body) {
        cm.sendMessageInConversation(conID, userID, body);
    }

    /**
     * Method for removing member with id membID from conversation with id conID
     * @param conID UUID of conversation
     * @param membID UUID of member that is being removed
     */
    public void RemoveMember(UUID conID, UUID membID) {
        cm.removeMember(conID, membID);
    }

    /**
     * Method for returning a list of the members by their username in conversation with id conID. Delete if not needed
     * @param conID UUID of conversation.
     * @return Returns ArrayList of member usernames.
     */
    public ArrayList<String> getMembersAsArray(UUID conID) {
        ArrayList<UUID> ids = cm.getMemberIDsInConversation(conID);
        ArrayList<String> names = new ArrayList<>();
        for (UUID id: ids) {
            names.add(um.getUserByID(id).getUsername());
        }
        return names;
    }
}
