package controllers.actions;

import controllers.actions.conversationActions.ViewConversationAction;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class GetArchivedConversationsAction extends GetConversationsAction {
    public GetArchivedConversationsAction(UUID userID, UserManager um, ConversationManager cm) {
        super(userID, um, cm);
    }

    public void run() {
        ArrayList<UUID> convos = cm.getUserConversationsArchived(userID);
        handleGetConversation("Archived Conversations", convos);
    }

    public String getName() {
        return "Get Archived Conversations";
    }
}
