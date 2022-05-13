package apServerCore.DATA.ApacheDerby;

import apCommon.ApMessageEnumType;

import java.sql.Timestamp;
import java.util.UUID;

public class Task {

    private ApMessageEnumType type;

    private UUID id;

    private Timestamp datetime;

    private int userID = 1;

    private String typeMessage = "1";

    private String message;

    public Task(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    public int getUserID() {
        return userID;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public String getMessage() {
        return message;
    }

    public UUID getId() {
        return id;
    }
}
