package UI;

import java.util.UUID;

public final class ConversationHolder {
    private UUID conversation;
    private String name;
    private final static ConversationHolder INSTANCE = new ConversationHolder();

    private ConversationHolder(){}

    public static ConversationHolder getInstance(){
        return INSTANCE;
    }

    public void setConversation(UUID convo, String name){
        this.conversation = convo;
        this.name = name;
    }

    public UUID getConversation(){
        return conversation;
    }

    public String getConversationName(){
        return name;
    }

}
