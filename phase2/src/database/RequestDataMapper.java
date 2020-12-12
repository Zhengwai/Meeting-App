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
            ResultSet rs = db.getAllFromTable("requests");
            ArrayList<Request> out = new ArrayList<>();
            while (rs.next()) {
                UUID requestingUser = UUID.fromString(rs.getString("uuid"));
                Request req = new Request(requestingUser, rs.getString("requestText"));
                String[] tagsList = db.parseArrayList((String) rs.getObject("tags"));

                if (tagsList != null) {
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
            db.updateTableRowValueStrings("requests", "tags", req.getRequestingUser(), new ArrayList<>(req.getTags()));
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that request's tags.");
            e.printStackTrace();
        }
    }

    @Override
    public void updateRequestResolved(Request req) {
        try {
            int val = 0;
            if (req.isResolved()) val = 1;

            db.updateTableRowValue("requests", "resolved", req.getRequestingUser(), val);
        } catch (SQLException e) {
            System.out.println("Something went wrong trying to update that's request's resolved bool.");
            e.printStackTrace();
        }
    }
}
