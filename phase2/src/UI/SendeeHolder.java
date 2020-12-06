package UI;

import java.util.UUID;

public final class SendeeHolder {
    private UUID sendee;
    private final static SendeeHolder INSTANCE = new SendeeHolder();

    private SendeeHolder(){}

    public static SendeeHolder getInstance(){
        return INSTANCE;
    }

    public void setSendee(UUID sendee){
        this.sendee = sendee;
    }

    public UUID getSendee(){
        return sendee;
    }

}
