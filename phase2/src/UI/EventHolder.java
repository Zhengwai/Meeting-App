package UI;

import java.util.UUID;

public final class EventHolder {
        private UUID event;
        private String name;
        private final static UI.EventHolder INSTANCE = new UI.EventHolder();

        private EventHolder(){}

        public static UI.EventHolder getInstance(){
            return INSTANCE;
        }

        public void setEvent(UUID event, String name){
            this.event = event;
            this.name = name;
        }

        public UUID getEvent(){
            return event;
        }

        public String getEventName(){
            return name;
        }

}
