package ap.DATA;

import ap.common.*;
import ap.Domain.FacadeServerDomain;
import ap.Presentation.FacadeServerPresentation;

import java.util.ArrayList;

public class FacadeServerDATA {
    public static boolean Authorization(String login,String password){
        return true;//Заглушка
    }

    //Идея в отм чтобы отправить в базу на запись текст и название канал а в ответ получить лист с логинами допущенных людей.
    public static ArrayList<String> AddChatChannelText(String login, String text, String channelName){
        return TheHUCK_GetLoginsListPassedToChannel();
    }


    //The HUCKS
    public static ArrayList<String> TheHUCK_GetLoginsListPassedToChannel(){
        ArrayList<String> loginsChannel = new ArrayList<>();
        loginsChannel.add("Admin");
        loginsChannel.add("Кабаков И.М.");
        loginsChannel.add("Артамонов С.В.");//Вроде правильно :), если ошибся поправь.
        return loginsChannel;
    }
}
