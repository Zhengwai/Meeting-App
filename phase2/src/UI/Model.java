package UI;

import entities.User;
import gateways.EventGateway;
import gateways.RequestGateway;
import gateways.UserGateway;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.RequestManager;
import use_cases.UserManager;

public final class Model {
    private static UserManager um;

    private static EventManager em;

    private static ConversationManager cm;

    private static RequestManager rm;

    private UserGateway ug = new UserGateway();
    private EventGateway eg = new EventGateway();
    private RequestGateway rg = new RequestGateway();

    private static User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Model() throws ClassNotFoundException {
        if(um == null | em == null | cm == null){
            initUC();
        }
    }

    public void initUC() throws ClassNotFoundException {
        this.um = new UserManager();
        this.em = new EventManager();
        this.cm = new ConversationManager();
    }

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
