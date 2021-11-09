package org.DATA;

public class Facade {
    public static void MessageFromServer(Message message) {
        org.Domain1.Facade.MessageFromServer(message);
    }
}
