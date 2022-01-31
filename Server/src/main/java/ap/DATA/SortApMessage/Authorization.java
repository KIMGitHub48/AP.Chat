package ap.DATA.SortApMessage;

import ap.common.ApMessage;
import ap.common.ApMetaMessage;

public class Authorization {
    private ApMessage apMessage;
    public Authorization(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public ApMetaMessage Process(){
        return new ApMetaMessage(apMessage);
    }
}
