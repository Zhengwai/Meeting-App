package gateways;

import use_cases.RequestManager;

import java.io.*;

/**
 * A gateway class that stores and reads information about attendee requests.
 */
public class RequestGateway{

    /**
     * A read method that reads a .ser file and returns the event manager stored.
     * @return the event manager stored at the file in filepath.
     */
    @SuppressWarnings("unchecked")
    public RequestManager deserializeRM(String filePath) throws ClassNotFoundException{
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            RequestManager rm = (RequestManager) input.readObject();
            input.close();
            return rm;
        } catch (IOException ex) {
            return new RequestManager();
        }
    }

    /**
     * A write method that writes an Request Manager object in to a .ser file at <code>filePath</code>
     * @param filePath the location of the .ser file.
     * @param rm the RequestManager to store.
     */
    public void serializeRM(String filePath, RequestManager rm) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rm);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


