package controllers.actions;

import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AddFriendAction extends MessageAction {
    private UUID friendID;

    public AddFriendAction(UUID userID, UserManager um, ConversationManager cm, UUID friendID) {
        super(userID, um, cm);
        this.friendID = friendID;
    }

    public void run() {
        if (friendID == um.NotFoundUser.getID()) {
            //System.out.println("That user doesn't exist!");
            return;
        }

        if (friendID == userID) {
            //System.out.println("You can't add yourself as a friend.");
            return;
        }

        for (UUID u:  um.getAllFriendIDs(userID)) {
            if (u.equals(friendID)) {
                //System.out.println("You already are friends with this user!");
                return;
            }
        }

        UUID conID = cm.newConversation();
        cm.addUserToConversation(conID, userID);
        cm.addUserToConversation(conID, friendID);
        um.addFriends(userID, friendID);
    }

    /*
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the username of the person you want to add:");
        String input = br.readLine();
        UUID newFriendID = um.getUserByName(input).getID();

        if (newFriendID == um.NotFoundUser.getID()) {
            System.out.println("That user doesn't exist!");
            return;
        }

        if (newFriendID == userID) {
            System.out.println("You can't add yourself as a friend.");
            return;
        }

        for (UUID u:  um.getAllFriendIDs(userID)) {
            if (u.equals(newFriendID)) {
                System.out.println("You already are friends with this user!");
                return;
            }
        }
        UUID conID = cm.newConversation();
        cm.addUserToConversation(conID, userID);
        cm.addUserToConversation(conID, newFriendID);
        um.addFriends(userID, newFriendID);
        System.out.println("You and " + um.getUserByID(newFriendID).getUsername() + " are now friends!");
    }
    */

    public String getName() {
        return "Add Friend";
    }
}

