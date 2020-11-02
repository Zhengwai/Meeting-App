package message_system;


import java.io.*;

public class MessageDataGateway {
    private MessageManager currentManager;

    public MessageManager getMessageManager(String path) {
        // TODO: Return the message manager requested by user from a MessageManager.ser file
        // Will need readFromFile method
        return null;
    }

    // Not sure if we need this, could be useful if we wanted to implement showing the User all their unread messages
    // or get pinned conversations or whatever we implement in the future.
    public MessageManager[] getAllMessageManagers() {
        // TODO: Return all message managers
        return null;
    }


    private void readFromFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            currentManager = (MessageManager) input.readObject();
            input.close();
        } catch (IOException ex) {
            // Will be discussed in lecture tomorrow:
           // logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }
    }
}

