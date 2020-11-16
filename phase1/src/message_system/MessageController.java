package message_system;


public class MessageController {
    // TODO: See the ReadWriteEx from Week 6 for how the Controller class should work
    private ConversationManager cm = ConversationGateway.readFromFile("cm.ser");
    private UUID userID;
    private String type;

    public MessageController(UUID id, String type) {
        this.userID = id;
        this.type = type;
    }

    public void run() {
        // Had to delete your code because the compiler was being a bully :(

        // This should have a while loop to handle user inputs to navigate through their conversations.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = br.readLine();
            while (!input.equals("exit")) {
                System.out.println("Issue Command or type exit to exit");
                input = br.readLine();
                if (input.equals("New Conversation")) {
                    System.out.println("Enter the UUIDs of Users in New Conversation. Once done enter exit.");
                    UUID conID = ConversationManager.newConversation();
                    Conversation c = ConversationManager.getConversation(conID);
                    c.addMember(userID);
                    String inp;
                    while (!input.equals("exit")) {
                        inp = br.readLine();
                        UUID id = UUID.fromString(inp);
                        c.addMember(id);
                    }
                } else if (input.equals("New Message")) {
                    System.out.println("Enter the conversation ID");
                    String inp = br.readLine();
                    UUID conID = UUID.fromString(inp);

                    System.out.println("Enter your Message");
                    inp = br.readLine();
                    Conversation c = ConversationManager.getConversation(conID);
                    Message msg = new Message(userID, inp);
                    c.sendMessage(msg);
                } else if (input.equals("Message All") && type = "Organizer") {
                    System.out.println("Enter your Message");
                    String inp = br.readLine();
                    //TODO: Retrieve list of users to message
                    ArrayList<User> recips;
                    UUID conID = ConversationManager.newConversation();
                    Conversation c = ConversationManager.getConversation(conID);
                    c.addMember(userID);
                    for (int i = 0; i < recips.size(); i++) {
                        c.addMember(recips[i].id);
                    }
                    Message msg = new Message(userID, inp);
                    c.sendMessage(msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }
}
