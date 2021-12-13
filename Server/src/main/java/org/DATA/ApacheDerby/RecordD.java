package org.DATA.ApacheDerby;

import java.util.Date;

public class RecordD {

    private int id;

    private Date datetime;

    private int userID;

    private int typeMessage;

    private String message;

    public RecordD(String message) {
        this.message = message;
    }

    public Date getDatetime() {
        return datetime;
    }

    public int getUserID() {
        return userID;
    }

    public int getTypeMessage() {
        return typeMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
