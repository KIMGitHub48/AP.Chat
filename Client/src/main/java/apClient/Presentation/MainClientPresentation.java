/*
setShowHideEvent(Stage stage) - Метод следит за открытыми окнами, если все окна закрыты завершает программу,
    метод необходим для закрытия потоков работающих отдельно от окон.
* */
package apClient.Presentation;

import apClient.Presentation.Controllers.ChatControllerClientPresentation;
import apClient.Presentation.Controllers.LoginPasswordControllerClientPresentation;
import apClient.Presentation.Controllers.OptionsControllerClientPresentation;
import apCommon.ApFinals;
import apCommon.ApMessage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Не является точкой входа в приложение.
 */
public class MainClientPresentation extends Application {
    public static MainClientPresentation mainPresentationRef;
    private ArrayList<Stage> listStage = new ArrayList<>();
    private ArrayList<String> listFileName = new ArrayList<>();
    private ArrayList<FXMLLoader> listFxmlLoader = new ArrayList<>();

    public MainClientPresentation() {
        mainPresentationRef = this;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LoadStage(true, ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoadStage(false, ApFinals.FXML_OPTIONS_FILE_NAME);
        LoadStage(false,ApFinals.FXML_CHAT_FILE_NAME);
    }

    public static void main(String[] args) {
        launch();
    }

    private void LoadStage(Boolean showOnStart, String fxmlName) {
        Scene scene;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(MainClientPresentation.class.getResource(fxmlName));
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            listStage.add(stage);
            listFileName.add(fxmlName);
            listFxmlLoader.add(fxmlLoader);
            if (showOnStart == true) {
                stage.show();
            }
            setCloseStageEvent(stage);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка загрузки сцены");
        }
    }

    private void setCloseStageEvent(Stage stage) {
        stage.setOnHidden(event -> {
            boolean allStageCloseFlag = true;
            for (int i = 0; i < listStage.size(); i++) {
                if (listStage.get(i).isShowing()) {
                    listStage.get(i).show();
                    allStageCloseFlag = false;
                    break;
                } else {
                    System.out.println("Сцена не видна");
                }
            }
            if (allStageCloseFlag){
                System.out.println("Stage is closing");
                System.exit(0);
            }
        });
    }//Событие закрытия окна проверяет есть ли хоть одно открытое окно иначе закрывает все потоки и приложение.

    public String GetIPFromTextField() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_OPTIONS_FILE_NAME);
        OptionsControllerClientPresentation controller = fxmlLoader.getController();
        return controller.GetIPFromTextField();
    }

    public Integer GetPortFromTextField() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_OPTIONS_FILE_NAME);
        OptionsControllerClientPresentation controller = fxmlLoader.getController();
        return controller.GetPortFromTextField();
    }

    public String GetLoginFromTextField() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoginPasswordControllerClientPresentation controller = fxmlLoader.getController();
        return controller.GetLoginFromTextField();
    }

    public String GetPasswordFromTextField() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoginPasswordControllerClientPresentation controller = fxmlLoader.getController();
        return controller.GetPasswordFromTextField();
    }

    public String GetButtonEnterText() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoginPasswordControllerClientPresentation controller = fxmlLoader.getController();
        return controller.GetButtonEnterText();
    }

    public void AddSystemMessage(String text) {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
        ChatControllerClientPresentation controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void chatChannelMessage(ApMessage apMessage) {
        System.out.println("Пришло сообщение");
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
        ChatControllerClientPresentation controller = fxmlLoader.getController();
        controller.setChatChannelMessage(apMessage.getChatChannelText(), apMessage.getChatChannelName());
    }

    public void SetButtonEnterTooltipTextAndShow(String text){
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoginPasswordControllerClientPresentation controller = fxmlLoader.getController();
        controller.SetButtonEnterTooltipTextAndShow(text);
    }

    public void ChangeButtonEnterTextAndDisable(String text, boolean disable) {
        LoginPasswordControllerClientPresentation controller = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME).getController();
        controller.ChangeButtonEnterTextAndDisable(text, disable);
    }

    private FXMLLoader GetFXMLLoader(String fileName){
        for (int i=0;i<listFileName.size();i++){
            if (fileName.equals(listFileName.get(i))){
                return listFxmlLoader.get(i);
            }
        }
        return null;
    }

    public void ShowHideOptionsStage(boolean show) {
        ShowHideStage(ApFinals.FXML_OPTIONS_FILE_NAME,show);
    }
    public void ShowHideChatStage(boolean show) {
        ShowHideStage(ApFinals.FXML_CHAT_FILE_NAME,show);
    }
    public void ShowHideLoginPasswordStage(boolean show) {
        ShowHideStage(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME,show);
    }
    private void ShowHideStage(String fileName, boolean show) {
        Stage stage;
        for (int i = 0; i < listStage.size(); i++) {
            if (fileName.equals(listFileName.get(i))) {
                if (show){
                    stage = listStage.get(i);
                    Platform.runLater(stage::show);
                } else
                {
                    stage = listStage.get(i);
                    Platform.runLater(stage::hide);
                }
            }
        }
    }
    public void ChangeLoginPasswordButtonEnter(String text, boolean disable){
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoginPasswordControllerClientPresentation loginPasswordControllerClientPresentation = fxmlLoader.getController();
        loginPasswordControllerClientPresentation.ChangeButtonEnterTextAndDisable(text, disable);
    }

    public String GetTextFromChatMessageTextField(){
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
        ChatControllerClientPresentation controller = fxmlLoader.getController();
        return controller.GetTextFromChatMessageTextField();
    }

}