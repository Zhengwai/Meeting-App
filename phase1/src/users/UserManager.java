package users;

import ScheduleSystem.Event;
import ScheduleSystem.EventManager;
import java.util.ArrayList;
import java.util.UUID;


public class UserManager {
    private ArrayList<User> allUsers;
    private EventManager em;
    private User currentUser;

    public UserManager(User currentUser, EventManager eventManager) {
        allUsers = new ArrayList<>();
        this.currentUser = currentUser;

    }

    public User getUserByID(UUID userid) throws NoUserFoundException {
        for (User u : allUsers){
            if (u.getID() == userid) {
                return u;
            }
        }

        throw new NoUserFoundException();
    }
    public boolean addFriend(User user1, User user2){
        if (!user2.isFriendsWith(user1.getID())){
            user1.addFriend(user2.getID());
            user2.addFriend(user1.getID());
            return true;
        }
        return false;
    }

    public boolean enrollInEvent(Event event){
        return em.signUpUser(currentUser, event);
    }

}

class NoUserFoundException extends Exception {
}
