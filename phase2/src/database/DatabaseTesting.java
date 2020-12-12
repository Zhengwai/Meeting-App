package database;

import entities.*;
import gateways.MessageDataGateway;
import gateways.RequestDataGateway;

import java.util.ArrayList;
import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        MessageDataGateway mdg = new MessageDataMapper();
        UUID dummyID1 = UUID.fromString("3691bcd0-b86b-4c04-ba94-816aa6f7b821");
        UUID dummyID2 = UUID.fromString("3691bcd0-b86b-4c04-ba94-816aa6f7b822");
        UUID dummyID3 = UUID.fromString("3691bcd0-b86b-4c04-ba94-816aa6f7b823");
        UUID dummyID4 = UUID.fromString("3691bcd0-b86b-4c04-ba94-816aa6f7b824");
        Conversation c = new Conversation();
        c.setConID(dummyID1);
        c.setOwner(dummyID2);
        c.setArchivedFor(dummyID3);
        c.setUnreadFor(dummyID4);
        mdg.insertConversation(c);
        mdg.updateConversationArchivedFor(c);
        mdg.updateConversationUnreadFor(c);

        ArrayList<Conversation> cons = mdg.fetchConversations();
        mdg.fetchMessages();
    }
}
