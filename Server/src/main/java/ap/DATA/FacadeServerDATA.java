package ap.DATA;

import ap.Domain.Net.Message;
import ap.Domain.FacadeServerDomain;
import ap.Presentation.FacadeServerPresentation;

public class FacadeServerDATA {
    public static void MessageFromClient(Message message){
        //Заглушка
        FacadeServerPresentation.AddSystemMessage("Пришло сообщение: ID:"+message.getId()+" Text:"+message.getChatChannelText()+" Channel:"+message.getChatChannelName());
        //message.setChatChannelText("На сервер пришло сообщение: ID:"+message.getId()+" Text:"+message.getChatChannelText()+" Channel:"+message.getChatChannelName());
        FacadeServerDomain.SendMessage(message);
    }
}
