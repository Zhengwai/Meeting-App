package database;

import entities.*;
import gateways.RequestDataGateway;

import java.util.UUID;

public class DatabaseTesting {
    public static void main(String[] args) {
        RequestDataGateway rdg = new RequestDataMapper();
        UUID dummyID = UUID.fromString("3691bcd0-b86b-4c04-ba94-816aa6f7b822");
        Request req = new Request(dummyID, "The request text.");
        req.addTag("Tag 1");
        rdg.insertRequest(req);
        req.addTag("Tag 2");
        rdg.updateRequestTags(req);
        for (Request r : rdg.fetchRequests()) {
            System.out.println(r.getRequestingUser() + " " + r.getTags().toString() + " " + r.getRequestText());
        }
    }
}
