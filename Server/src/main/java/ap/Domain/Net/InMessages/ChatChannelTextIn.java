package ap.Domain.Net.InMessages;

import ap.Domain.Net.OutMessages.AuthorizationOut;
import ap.Domain.Net.OutMessages.ChatChannelTextOut;
import ap.Domain.Net.OutMessages.SendMessages.CheckPassedLoginsListInThread;
import ap.common.ApMessage;

import java.net.Socket;
import java.util.ArrayList;

public class ChatChannelTextIn {
    private ApMessage apMessage;
    public ChatChannelTextIn(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public void Process(){
        String login = apMessage.getLogin();
        String password = apMessage.getPassword();
        String text = apMessage.getChatChannelText();
        String channelName = apMessage.getChatChannelName();
        boolean authorizationPassed = ap.DATA.FacadeServerDATA.Authorization(login, password);
        if (authorizationPassed) {
            apMessage.setAuthorizationPassed(false);
            ArrayList<String> passedLoginsList = new ArrayList<>();
            passedLoginsList = ap.DATA.FacadeServerDATA.AddChatChannelText(login,text,channelName);
            CheckPassedLoginsListInThread checkPassedLoginsListInThread = new CheckPassedLoginsListInThread(apMessage, passedLoginsList);
        } else {
            //TODO Послать сообщение для того чтоб клиент разлогинился.
        }
    }
}
