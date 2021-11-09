package org.DATA;

public class Facade {
    public static void MessageFromServer(Message message) {
        org.Domain.Facade.MessageFromServer(message);
    }
}
