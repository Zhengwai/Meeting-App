package controllers.actions.conversationAction;

import presenters.MessagePresenter;
import use_cases.ConversationManager;
import use_cases.UserManager;

import java.util.UUID;

public abstract class ConversationAction {
    protected UUID userID;
    protected UserManager um;
    protected ConversationManager cm;
    protected MessagePresenter mp;
    protected UUID convID;

    public ConversationAction(UUID userID, UserManager um, ConversationManager cm, UUID convID) {
        this.userID = userID;
        this.cm = cm;
        this.um = um;
        this.convID = convID;
    }

    public abstract void run() throws Exception;
    public abstract String getName();
}
