package ap.DATA.SortApMessage;

import ap.common.ApMessage;
import ap.common.ApMetaMessage;

public class ChatChannelText {
    private ApMessage apMessage;
    public ChatChannelText(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public ApMetaMessage Process(){
        return new ApMetaMessage(apMessage);
    }
}
