package entities;

import java.io.Serializable;

public class VIP extends User implements Serializable {
    private String type;
    public VIP(String username, String password){
        super(username, password);
        type = "v";
    }

    @Override
    public String getType(){
        return type;
    }
}
