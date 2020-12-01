package UI;

import gateways.EventGateway;
import gateways.UserGateway;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.UserManager;

public class Model {
    private UserManager um;

    private EventManager em;

    private ConversationManager cm;

    private UserGateway ug = new UserGateway();
    private EventGateway eg = new EventGateway();

    public Model() throws ClassNotFoundException {
        this.um = ug.deserializeUserManager("phase2/src/use_cases/UserManager.ser");
        this.em = eg.deserializeEM("phase2/src/use_cases/EventManager.ser");
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
