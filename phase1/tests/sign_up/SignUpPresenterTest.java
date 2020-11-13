import java.util.Date;
import ScheduleSystem.Event;
import users.User;
import user_controllers.AttendeeController;

public class SignUpPresenterTest {

    public void testAlreadySignedUp(){
        Date test_Date = new Date();
        Event event1 = new Event("Event 1", test_Date, 2);
        Event full = new Event("Same Day", test_Date, 2);
        User gabe = new User("Gabe", "B", "gb@gmail.com", "gb", "Attendee");
        User ash = new User("Ash", "J", "aj@gmail.com", "aj", "Attendee");
        User lily = new User("Lily", "R", "LR@gmail.com", "lr", "Attendee");

        AttendeeController ac = new AttendeeController(lily);
        event1.addAttendee(lily);

        ac.signUp();
    }

}
