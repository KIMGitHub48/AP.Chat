package org.DATA.ApacheDerby;

import java.sql.Timestamp;

public class Record {

    private int id;

    private Timestamp datetime;

    private int userID = 1;

    private String typeMessage = "1";

    private String message;

    public Record(String message) {
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

    public void setMessage(String message) {
        this.message = message;
    }
}
