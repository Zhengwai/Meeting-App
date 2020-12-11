package database;

import entities.Request;
import gateways.RequestDataGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class RequestDataMapper implements RequestDataGateway {
    private Database db = new Database();

    @Override
    public ArrayList<Request> fetchRequests() {
        try {
            ResultSet rs = db.getAllRequests();
            ArrayList<Request> out = new ArrayList<>();
            while (rs.next()) {
                UUID requestingUser = UUID.fromString(rs.getString("requestingUser"));
                Request req = new Request(requestingUser, rs.getString("requestText"));
                String rawTags = (String) rs.getObject("tags");

                if (rawTags != null) {
                    rawTags = rawTags.substring(1, rawTags.length() - 1);
                    String[] tagsList = rawTags.split(", ");

                    for (String tag : tagsList) {
                        req.addTag(tag);
                    }
                }

                int res = rs.getInt("resolved");
                if (res == 1) req.markResolved();
                else req.unmarkResolved();

                out.add(req);
            }

            return out;
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to get all requests.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void insertRequest(Request req) {
        try {
            db.insertRequest(req.getRequestingUser(), req.getRequestText(), new ArrayList<>(req.getTags()), req.isResolved());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to insert that request.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateRequestTags(Request req) {
        try {
            db.updateRequestTags(req.getRequestingUser(), new ArrayList<>(req.getTags()));
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that request's tags.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateRequestResolved(Request req) {
        try {
            db.updateRequestResolved(req.getRequestingUser(), req.isResolved());
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that's request's resolved bool.");
            e.printStackTrace();
        }
    }
}
