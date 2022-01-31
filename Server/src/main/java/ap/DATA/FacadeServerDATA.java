package ap.DATA;

import ap.DATA.SortApMessage.SortApMessage;
import ap.common.*;
import ap.Domain.FacadeServerDomain;
import ap.Presentation.FacadeServerPresentation;

import java.util.ArrayList;

public class FacadeServerDATA {
    //Это будет основной метод для общения с базой.
    //Метод должен принять сообщение обработать и создать ApMetaMessage, заполнить его и вернуть.
    public static ApMetaMessage MessageToData(ApMessage apMessage){
        SortApMessage sortApMessage = new SortApMessage(apMessage);
        return sortApMessage.Sort();
    }

    public static boolean Authorization(String login,String password){
        return true;//Заглушка
    }

    //Идея в отм чтобы отправить в базу на запись текст и название канал а в ответ получить лист с логинами допущенных людей.
    public static ArrayList<String> AddChatChannelText(String login, String text, String channelName){
        return TheHUCK_GetLoginsListPassedToChannel();
    }


    //The HUCKS - заглушки для методов
    public static ArrayList<String> TheHUCK_GetLoginsListPassedToChannel(){
        ArrayList<String> loginsChannel = new ArrayList<>();
        loginsChannel.add("Admin");
        loginsChannel.add("Кабаков И.М.");
        loginsChannel.add("Артамонов С.В.");//Вроде правильно :), если ошибся поправь.
        return loginsChannel;
    }

    public static ApMetaMessage TheHUCK_ApMetaMessage(ApMessage apMessage){
        return new ApMetaMessage(apMessage);
    }
}
