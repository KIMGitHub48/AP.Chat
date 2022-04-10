package apClientUI;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface AuthorizationService {
    //Метод возвращает первую имплиментацию этого интерфейса.
    static AuthorizationService getFirst() {
        List<AuthorizationService> authorizationServiceList = ServiceLoader
                .load(AuthorizationService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        return authorizationServiceList.get(0);
    }

    void Launcher();

    void SetButtonEnterTooltipTextAndShow(String text);

    void HideAuthorizationStage();

    void ShowAuthorizationStage();

    String GetButtonEnterText();

    void ChangeAuthorizationButtonEnterTextAndDisable(String text, boolean disable);

    void AuthorizationResponseActionConnectionError();

    void AuthorizationResponseActionTimeError();

    void AuthorizationResponseActionNotPassed();

    void AuthorizationResponseActionPassed();
}
