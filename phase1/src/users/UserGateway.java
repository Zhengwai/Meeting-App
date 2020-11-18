package users;

import java.io.*;

public class UserGateway {

    public UserManager readFromFile() throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream("/phase1/userManager.ser");
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
    public void saveToFile(UserManager um) throws IOException {
        OutputStream file = new FileOutputStream("/phase1/userManager.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(um);
        output.close();
    }
}
