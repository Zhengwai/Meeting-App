package message_system;


import java.io.*;

public class MessageDataGateway {
    private MessageManager currentManager;

    public MessageManager getMessageManager(String path) {
        // TODO: Return the message manager requested by user from a MessageManager.ser file
        // Will need readFromFile method
        return readFromFile(path);
    }

    // Not sure if we need this, could be useful if we wanted to implement showing the User all their unread messages
    // or get pinned conversations or whatever we implement in the future.
    public MessageManager[] getAllMessageManagers() {
        // TODO: Return all message managers
        return null;
    }

    public void saveToFile(String path, MessageManager msgMgr) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputSream(buffer);

        output.writeObject(msgMgr);
        output.close();
    }


    private Messagemanager readFromFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            MessageManager currentManager = (MessageManager) input.readObject();
            input.close();
            return currentManager;
        } catch (IOException ex) {
            logger.log(level.SEVER, "Cannot read from input file, returning a new MessageManager.", ex);
            return new MessageManager();
            // Will be discussed in lecture tomorrow:
           // logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }
    }
}

