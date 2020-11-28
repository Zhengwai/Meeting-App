package controllers.actions;

import entities.User;
import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.UUID;

public abstract class MessageAction {
    protected UUID userID;
    protected UserManager um;
    protected ConversationManager cm;
    protected MessagePresenter mp;

    public MessageAction(UUID userID, UserManager um, ConversationManager cm) {
        this.userID = userID;
        this.cm = cm;
        this.um = um;
    }

    public abstract void run() throws Exception;
    public abstract String getName();
}
