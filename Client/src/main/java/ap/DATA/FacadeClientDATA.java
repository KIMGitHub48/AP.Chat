package ap.DATA;

import ap.Domain.FacadeClientDomain;
import ap.Domain.Net.Message;

public class FacadeClientDATA {
    public static void MessageFromServer(Message message) {
        FacadeClientDomain.MessageFromServer(message);
    }
}
