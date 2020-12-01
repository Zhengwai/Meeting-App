package UI;

import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.UserManager;

public class Model {
    private UserManager um;

    private EventManager em;

    private ConversationManager cm;

    public UserManager getUm() {
        return um;
    }

    public EventManager getEm() {
        return em;
    }

    public ConversationManager getCm() {
        return cm;
    }
}
