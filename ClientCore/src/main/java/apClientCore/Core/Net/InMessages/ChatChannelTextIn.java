package apClientCore.Core.Net.InMessages;

//import apClientCore.Presentation.FacadeClientPresentation;
import apCommon.ApMessage;

public class ChatChannelTextIn {
    private ApMessage apMessage;
    public ChatChannelTextIn(ApMessage chatChannelApMessage){
        apMessage = chatChannelApMessage;
    }
    public void Process(){
        //FacadeClientPresentation.chatChannelMessage(apMessage);
    }
}
