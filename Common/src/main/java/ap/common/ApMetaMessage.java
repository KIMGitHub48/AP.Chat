package ap.common;

import java.util.ArrayList;

public class ApMetaMessage {
    private ApMessage apMessage;
    private ArrayList<String> loginsPassedToChannel = new ArrayList<>(); //Лист с логинами которым будет отправляться это сообщение.
    public ApMetaMessage(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public ArrayList<String> getLoginsPassedToChannel(){
        return loginsPassedToChannel;
    }

    public void addToLoginsPassedToChannel(String login){
        loginsPassedToChannel.add(login);
    }

    public ApMessage getApMessage() {
        return apMessage;
    }

    public void setAuthorizationPassed(boolean passed){
        apMessage.setAuthorizationPassed(passed);
    }

    public boolean isAuthorizationPassed(){
        return apMessage.isAuthorizationPassed();
    }
}
