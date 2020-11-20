package message_system;

import java.io.*;

/**
 * Gateway class.
 * Handles reading from and writing to the local 'cm.ser' file.
 */
public class ConversationGateway {

    /**
     * Deserializes the cm.ser file into a ConversationManager.
     * @param filePath The location of where the cm.ser file is saved
     * @return The deserialized ConversationManager
     * @throws ClassNotFoundException If there is no cm.ser file
     */
    public ConversationManager readFromFile(String filePath) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            ConversationManager cm = (ConversationManager) input.readObject();
            input.close();
            return cm;
        } catch (IOException ex) {
            return new ConversationManager();
        }
    }

    /**
     * Serializes the ConversationManager into a cm.ser file.
     * @param filePath The location of where the cm.ser file is going to be saved
     * @param cm The ConversationManager to be serialized
     * @throws IOException If something goes wrong during serialization
     */
    public void saveToFile(String filePath, ConversationManager cm) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(cm);
        output.close();
    }

}
