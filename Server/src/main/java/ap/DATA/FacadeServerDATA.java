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
//        SortApMessage sortApMessage = new SortApMessage(apMessage);
//        return sortApMessage.Sort();
        return TheHUCK_ApMetaMessage(apMessage);
    }




    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    //The HUCKS - заглушки для методов
    public static ArrayList<String> TheHUCK_GetLoginsListPassedToChannel(){
        ArrayList<String> loginsChannel = new ArrayList<>();
        loginsChannel.add("Admin");
        loginsChannel.add("Кабаков И.М.");
        loginsChannel.add("Артамонов С.В.");//Вроде правильно :), если ошибся поправь.
        return loginsChannel;
    }

    public static ApMetaMessage TheHUCK_ApMetaMessage(ApMessage apMessage){
        System.out.println(apMessage.getLogin());
        ApMetaMessage apMetaMessage = new ApMetaMessage(apMessage);
        if (apMessage.getLogin().equals("Провал")) {
            apMetaMessage.setAuthorizationPassed(false);
        } else {
            apMetaMessage.setAuthorizationPassed(true);
        }
        apMetaMessage.addToLoginsPassedToChannel("Admin");
        apMetaMessage.addToLoginsPassedToChannel("Илья");
        apMetaMessage.addToLoginsPassedToChannel("Стас");
        return apMetaMessage;
    }
}
