package apClientUI;

import apCommon.ApFinals;
import javafx.fxml.FXMLLoader;

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

    void SetButtonEnterTooltipTextAndShow(String text);

    void ChangeButtonEnterTextAndDisable(String text, boolean disable);

    void Hide();

    void Show();
}
