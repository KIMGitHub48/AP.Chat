package ap.DATA.ApacheDerby;

import java.sql.Timestamp;
import java.util.UUID;

public class Log {

    private UUID id = UUID.randomUUID();

    private Timestamp datetime;

    private String logMessage;

    enum Type {UserMessage, PrivateMessage, UserTask, TechLog, ActivityLog}  // Типы логов через перечисление, может позже откажусь от этого варианта
                                                                             // Лог пользовательских сообщений, Лог приватных сообщений, Лог пользовательских заданий, Технический лог, Лог активности
    public Log(String logMessage) {
        this.logMessage = logMessage;
    }


    public String getDatetime() {
        Timestamp datetime = new Timestamp(System.currentTimeMillis());
        return datetime.toString();
    }
}
