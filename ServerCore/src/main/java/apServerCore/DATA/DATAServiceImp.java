package apServerCore.DATA;

import apServerCore.DATA.ApacheDerby.ApacheDerby;
import apCommon.ApMessage;
import apCommon.ApMetaMessage;

public class DATAServiceImp implements DATAService{
    public ApMetaMessage MessageToData(ApMessage apMessage){
        ApacheDerby.addRecord(apMessage.getType(), apMessage.getUUID(), apMessage.getChatChannelText());
        //TODO СТАСУ: Замени метод TheHUCK_ApMetaMessage(apMessage); на свой и отметь гдето в комментах рабочий логин и пароль
        return TheHUCK_ApMetaMessage(apMessage);
    }

    public ApMetaMessage TheHUCK_ApMetaMessage(ApMessage apMessage){
        System.out.println(apMessage.getLogin());
        ApMetaMessage apMetaMessage = new ApMetaMessage(apMessage);
        if (!ApacheDerby.authorization(apMessage.getLogin(), apMessage.getPassword())) {    // Проверка авторизации
            apMetaMessage.setAuthorizationPassed(false);
        } else {
            apMetaMessage.setAuthorizationPassed(true);
        }
        apMetaMessage.addToLoginsPassedToChannel("Admin");
        apMetaMessage.addToLoginsPassedToChannel("Илья");
        apMetaMessage.addToLoginsPassedToChannel("Стас");
        return apMetaMessage;
    }
}
