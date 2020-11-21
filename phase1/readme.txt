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


=== Signup System ===


=== Users ===
Types of users are denoted with a type of "o" for Organizer, "a" for Attendee and "s" for Speaker. These types are used
for determining which types of controllers to instantiate.