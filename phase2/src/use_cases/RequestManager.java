package use_cases;

import entities.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Use case class for special user requests
 */
public class RequestManager implements Serializable {
    private ArrayList<Request> requestArrayList;

    /**
     * Creates new RequestManager
     */
    public RequestManager(){
        this.requestArrayList = new ArrayList<>();
    }

    /**
     * add a new request to the request manager
     * @param request the request we are adding to the manager
     */
    public void addRequest(Request request){
        this.requestArrayList.add(request);
    }

    /**
     * @return all requests in the RequestManager
     */
    public ArrayList<Request> getRequestArrayList(){
        return this.requestArrayList;
    }
    
    /**
     * return all the tags such that at least one request in the request manager has such a tag
     * @return an HashSet of tags
     */
    public HashSet<String> getAllTags() {
        HashSet<String> tags = new HashSet<>();

        for (Request request : this.requestArrayList) {
            tags.addAll(request.getTags());
        }
        return tags;
    }

    /**
     * search the RequestManager for all requests with the specified tag. If no requests with specified tag,
     * return an empty ArrayList.
     * @param tag the tag we are searching for
     * @return an ArrayList of the request with the specified tag
     */
    public ArrayList<Request> getRequestByTag(String tag){
        ArrayList<Request> filteredRequests = new ArrayList<>();
        for (Request request : this.requestArrayList){
           if (request.getTags().contains(tag)){
                filteredRequests.add(request);        
           }
        }
        return getRequestArrayList();
    }
}

