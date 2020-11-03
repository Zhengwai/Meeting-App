package users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private UUID userID = UUID.randomUUID();
    private String username;
    private String password;
    private Map<Integer, Integer> chatList = new HashMap<Integer, Integer>();
    private String email = "";
    private String type = "user";

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getChatHistoryID(int friendID) {
        return chatList.get(friendID);
    }

    public ArrayList<Integer> getFriendsIDs(){
        return new ArrayList<Integer>(chatList.keySet());
    }

}
