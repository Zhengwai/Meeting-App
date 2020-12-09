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

    public void setConversation(UUID convo){
        this.conversation = convo;
    }

    public UUID getConversation(){
        return conversation;
    }

    public void setConversationName(String name){
        this.name = name;
    }

    public String getConversationName(){
        return name;
    }

}
