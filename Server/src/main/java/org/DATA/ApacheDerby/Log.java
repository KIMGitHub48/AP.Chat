package org.DATA.ApacheDerby;

import java.sql.Timestamp;

public class Log {

    private int id;

    private Timestamp datetime;

    private String logMessage;

    public Log(String logMessage) {
        this.logMessage = logMessage;
    }


    public String getDatetime() {
        Timestamp datetime = new Timestamp(System.currentTimeMillis());
        return datetime.toString();
    }
}
