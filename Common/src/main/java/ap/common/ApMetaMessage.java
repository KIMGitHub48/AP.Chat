package ap.common;

import java.util.ArrayList;

public class ApMetaMessage {
    public ApMessage apMessage;
    private boolean isAuthorizationPassed; //Отметка об успешной или неуспешной авторизации.
    private ArrayList<String> loginsPassedToChannel = new ArrayList<>(); //Лист с логинами которым будет отправляться это сообщение.
    public ApMetaMessage(ApMessage apMessage){
        this.apMessage = apMessage;
    }

}
