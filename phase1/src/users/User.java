package users;

import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable {
    private UUID id = UUID.randomUUID();
    private String username;
    private String email = "";
    private String password;
    private String type = "User";
    private ArrayList<UUID> enrolledEvents = new ArrayList<UUID>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}

    public String getType(){return this.type;}

    public String getUsername(){return this.username;}

    public UUID getID(){return this.id;}

    public ArrayList<UUID> getEnrolledEvents(){
        return this.enrolledEvents;
    }

    @Override
    public String toString(){
        return type + ":" + " " + username;
    }
}
