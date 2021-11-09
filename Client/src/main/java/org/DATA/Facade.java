package org.DATA;

public class Facade {
    public static void MessageFromServer(org.Domain.Net.Message message) {
        org.Domain.Facade.MessageFromServer(message);
    }
}
