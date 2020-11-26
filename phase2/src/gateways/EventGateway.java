package gateways;

import use_cases.EventManager;

import java.io.*;

/**
 * A gateway class that stores and reads information about events and rooms.
 */
public class EventGateway {

    @SuppressWarnings("unchecked")
    /**
     * A read method that reads a .ser file and returns the event manager stored.
     * @returns the event manager stored at the file in filepath.
     */
    public EventManager deserializeEM(String filePath) throws ClassNotFoundException{
        try {
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            EventManager em = (EventManager) input.readObject();
            input.close();
            return em;
        } catch (IOException ex) {
            return new EventManager();
        }
    }

    /**
     * A write method that writes an Event Manager object in to a .ser file at <code>filePath</code>
     * @param filePath the location of the .ser file.
     * @param em the EventManager to store.
     */
    public void serializeEM(String filePath, EventManager em) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(em);

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    }
    

