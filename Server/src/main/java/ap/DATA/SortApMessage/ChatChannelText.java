package ap.DATA.SortApMessage;

import ap.common.ApMessage;
import ap.common.ApMetaMessage;

//Обязательно докрутить авторизацию, я буду проверять прошло ли это сообщение авторизацию.
//Тоесть сначала проверить логин и пароли потом обработка сообщения.

public class ChatChannelText {
    private ApMessage apMessage;
    public ChatChannelText(ApMessage apMessage){
        this.apMessage = apMessage;
    }

    public ApMetaMessage Process(){
        return new ApMetaMessage(apMessage);
    }
}
