package ap.DATA;

import ap.Domain.Net.Message;

public class Facade {
    public static void MessageFromServer(Message message) {
        ap.Domain.Facade.MessageFromServer(message);
    }
}
