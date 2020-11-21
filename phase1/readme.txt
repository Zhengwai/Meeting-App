Project Phase 1

The program is designed to provide the user with enough information via console prompts for easy use.
Below is some additional information about how each part of the program works.

== Login System ===
This system is used first when the program starts running. It is here where you may register new users and login to
access the rest of the program. Registered users are added to the UserManager and is immediately serialized.


=== Message System ===
To start messaging people you need to add friends! Upon adding a friend, a new conversation gets created between you
two. Organizers can bypass this with their ability to message all given Users in an event.
Serialization of the ConversationManager occurs at the end of the MessageController's run() method.


=== Saving and Persistence of Data ===
There are several usages of serialization for saving data:
    ConversationManager is serialized to ./phase1/cm.ser
    EventManager is serialized to ./phase1/em.ser
    UserManager is serialized to ./phase1/um.ser

The program generates these files if they don't exist on your local machine. The program needs to be exited through the
program's main screen via the console, otherwise it is not guaranteed that data will be saved properly.

=== Schedule System ===
Organizers can create events, rooms and assign them in the event management tab once they login. Note that all events
must start on the hour between 9 to 16 and lasts one hour long. Rooms that have the event held on one specific time
period cannot be assigned to any other events in the same time period.

Organizers can also create account for the speaker of the events and assign them to each event. A speaker can only be
present at one event in the same time period.
=== Signup System ===
Attendees can sign up for events in the event tab once they login. The program will generate a list of events that
the user hasn't signed up and are not full to the user.

Attendee can only sign up for one event for every time period.
=== Users ===
Types of users are denoted with a type of "o" for Organizer, "a" for Attendee and "s" for Speaker. These types are used
for determining which types of controllers to instantiate.