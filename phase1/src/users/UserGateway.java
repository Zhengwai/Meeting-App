package users;

import java.io.*;

public class UserGateway {

    public UserManager readFromFile(String filePath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            UserManager um = (UserManager) input.readObject();
            input.close();
            return um;
        } catch (IOException ex) {
            //TODO: Needs logger
            return new UserManager();
        }
    }

    /**
     * Serializes the UserManager into a cm.ser file.
     * @param um The UserManager to be serialized
     * @throws IOException If something goes wrong during serialization
     */
    public void saveToFile(String filePath, UserManager um) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(um);
        output.close();
    }
}
