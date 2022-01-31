package ap.Domain.Net.OutMessages.SendMessages;

import ap.common.ApMessage;

import java.util.ArrayList;
import java.util.List;

public class CheckPassedLoginsListInThread extends Thread {
    ArrayList<String> passedLogins;
    ApMessage apMessage;

    public CheckPassedLoginsListInThread(ApMessage apMessage, ArrayList<String> passedLogins){
        this.passedLogins = passedLogins;
        this.apMessage = apMessage;
    }

    @Override
    public void run(){
        SendMessageToSocketInThread sendMessageToSocketInThread = new SendMessageToSocketInThread();
        sendMessageToSocketInThread.Send();
    }
}
