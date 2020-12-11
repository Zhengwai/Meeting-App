package gateways;

import entities.Request;

import java.util.ArrayList;

public interface RequestDataGateway {
    /**
     * Gets all requests stored in the database.
     * @return ArrayList of all requests
     */
    ArrayList<Request> fetchRequests();

    /**
     * Insert a new request in the database.
     * @param req The request to be saved.
     */
    void insertRequest(Request req);

    /**
     * Updates the tags field of a request in the database.
     * @param req The request to be updated.
     */
    void updateRequestTags(Request req);

    /**
     * Updates the resolved field of a request in the database.
     * @param req The request to be updated.
     */
    void updateRequestResolved(Request req);
}
