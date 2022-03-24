package ap.DATA.SortApMessage;

import apCommon.ApMessage;
import apCommon.ApMetaMessage;

public class Authorization {
    private ApMessage apMessage;
    public Authorization(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public ApMetaMessage Process(){
        return new ApMetaMessage(apMessage);
    }
}
