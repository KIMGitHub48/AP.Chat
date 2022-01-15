package ap.DATA;

import ap.Domain.FacadeClientDomain;
import ap.common.*;

public class FacadeClientDATA {
    public static void MessageFromServer(ApMessage apMessage) {
        //Заглушка
        Zaglushka(apMessage);
    }
    public static void Zaglushka(ApMessage apMessage){
        String text = apMessage.getChatChannelText();
        text = "(Отметка с севера) "+text;
        apMessage.setChatChannelText(text);
        FacadeClientDomain.MessageFromServer(apMessage);
    }
}
