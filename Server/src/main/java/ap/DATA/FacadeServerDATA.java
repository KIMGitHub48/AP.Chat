package ap.DATA;

import ap.common.*;
import ap.Domain.FacadeServerDomain;
import ap.Presentation.FacadeServerPresentation;

public class FacadeServerDATA {
    public static void MessageFromClient(ApMessage apMessage){
        //Заглушка
        Zaglushka(apMessage);
    }
    public static void Zaglushka(ApMessage apMessage){
        FacadeServerPresentation.AddSystemMessage("Пришло сообщение: ID:"+apMessage.getType()+" Text:"+apMessage.getChatChannelText()+" Channel:"+apMessage.getChatChannelName());
        //message.setChatChannelText("На сервер пришло сообщение: ID:"+message.getId()+" Text:"+message.getChatChannelText()+" Channel:"+message.getChatChannelName());
        FacadeServerDomain.SendMessage(apMessage);
    }
}
