package ap.DATA.SortApMessage;

import apCommon.ApMessage;
import apCommon.ApMessageEnumType;
import apCommon.ApMetaMessage;

//Для каждого типа сообщения будет создан свой класс.

public class SortApMessage {
    private ApMessage apMessage;
    public SortApMessage(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public ApMetaMessage Sort(){
        ApMessageEnumType apMessageEnumType = apMessage.getType();
        switch(apMessageEnumType){
            case authorization:
                Authorization authorization = new Authorization(apMessage);
                return authorization.Process();
            case chatChannelText:
                ChatChannelText chatChannelText = new ChatChannelText(apMessage);
                return chatChannelText.Process();
            default:
                return null;
        }
    }
}
