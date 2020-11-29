package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for a specific user request
 */
public class Request {
    private User requestingUser;
    private String requestText;
    private HashSet<String> tags;
    private boolean resolved;
    // TODO: 11/28/2020 try catch for illegal arguments

    /**
     * Creates a new request with a User and text. A new request has no tags initially.
     * @param requestingUser user whom is requesting the request
     * @param requestText a text description of the request
     */
    public Request(User requestingUser, String requestText){
        this.requestingUser = requestingUser;
        this.requestText = requestText;
        this.tags = new HashSet<>();
        this.resolved = false;
    }

    /**
      * @return the user whom requested this request
     */
    public User getRequestingUser(){
        return this.requestingUser;
    }

    /**
     * @return the text of the request
     */
    public String getRequestText(){
        return this.requestText;
    }

    /**
     * @return the tags associated with the request
     */
    public Set<String> getTags(){
        return this.tags;
    }

    /**
     * @return true if and only if the request has been marked resolved
     */
    public boolean isResolved(){
        return resolved;
    }

    /**
     * change the text of the request
     * @param newText the new request text
     */
    public void editRequestText(String newText){
        this.requestText = newText;
        // TODO: 11/28/2020 try catch for illegal input
    }

    /**
     * add a tag to the request
     * @param tag the tag being added
     */
    public void addTag(String tag){
        this.tags.add(tag);
    }

    /**
     * Attempt to remove a tag from the request
     * @param tag the tag being removed
     * @return true if and only if the tag was removed from the request
     */
    public boolean removeTag(String tag){
        if (this.tags.contains(tag)){
            this.tags.remove(tag);
            return true;
        }
        else{
            return false;
        }
    }


}
