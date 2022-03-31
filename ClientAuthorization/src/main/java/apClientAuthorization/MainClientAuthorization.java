package apClientAuthorization;

import apClientAuthorization.Controllers.AuthorizationController;
import apClientAuthorization.Messages.In.*;
import apCommon.ApFinals;
import apCommon.apModuleServices.ClientCoreService;
import apCommon.apModuleServices.ClientCoreMapService;
import apCommon.apModuleServices.ClientOptionsService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainClientAuthorization extends Application {
    public static MainClientAuthorization mainClientAuthorizationRef;
    private ArrayList<Stage> listStage = new ArrayList<>();
    private ArrayList<String> listFileName = new ArrayList<>();
    private ArrayList<FXMLLoader> listFxmlLoader = new ArrayList<>();
    private ClientCoreService clientCoreService = apCommon.apModuleServices.ClientCoreService.getFirst();
    private ClientCoreMapService clientCoreMapService = apCommon.apModuleServices.ClientCoreMapService.getFirst();
    private ClientOptionsService clientOptionsService = apCommon.apModuleServices.ClientOptionsService.getFirst();
    private boolean IsAuthorizationAvailable=false;//Поле регулирует доступность авторизации для клиента, если false то ответ от сервера для авторизации будет игнорироваться

    public MainClientAuthorization() {
        mainClientAuthorizationRef = this;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoadStage(true, ApFinals.FXML_AUTHORIZATION_FILE_NAME);
    }


    private void LoadStage(Boolean showOnStart, String fxmlName) {
        Scene scene;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(MainClientAuthorization.class.getResource(fxmlName));
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

    //TODO Как закрывать программу?
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

    public String GetLoginFromTextField() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
        AuthorizationController authorizationController = fxmlLoader.getController();
        return authorizationController.GetLoginFromTextField();
    }

    public String GetPasswordFromTextField() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
        AuthorizationController authorizationController = fxmlLoader.getController();
        return authorizationController.GetPasswordFromTextField();
    }

    public String GetButtonEnterText() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
        AuthorizationController authorizationController = fxmlLoader.getController();
        return authorizationController.GetButtonEnterText();
    }

//    public void AddSystemMessage(String text) {
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
//        ChatControllerClientPresentation controller = fxmlLoader.getController();
//        controller.SetSystemText(text);
//    }

//    public void chatChannelMessage(ApMessage apMessage) {
//        System.out.println("Пришло сообщение");
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
//        ChatControllerClientPresentation controller = fxmlLoader.getController();
//        controller.setChatChannelMessage(apMessage.getChatChannelText(), apMessage.getChatChannelName());
//    }

    public void SetButtonEnterTooltipTextAndShow(String text){
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
        AuthorizationController authorizationController = fxmlLoader.getController();
        authorizationController.SetButtonEnterTooltipTextAndShow(text);
    }

    public void ChangeButtonEnterTextAndDisable(String text, boolean disable) {
        AuthorizationController authorizationController = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME).getController();
        authorizationController.ChangeButtonEnterTextAndDisable(text, disable);
    }

    private FXMLLoader GetFXMLLoader(String fileName){
        for (int i=0;i<listFileName.size();i++){
            if (fileName.equals(listFileName.get(i))){
                return listFxmlLoader.get(i);
            }
        }
        return null;
    }

    private Stage GetStage(String fileName) {
        for (int i = 0; i < listFileName.size(); i++) {
            if (fileName.equals(listFileName.get(i))) {
                return listStage.get(i);
            }
        }
        return null;
    }
    public void ShowOptionsStage(){
        clientCoreMapService.ShowOptionsStage();
    };

    public void ChangeAuthorizationButtonEnterTextAndDisable(String text, boolean disable){
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
        AuthorizationController authorizationController = fxmlLoader.getController();
        authorizationController.ChangeButtonEnterTextAndDisable(text, disable);
    }

    public boolean IsConnected() {
        return clientCoreService.IsConnected();
    }

    public void ConnectToServer() {
        Runnable mainRunnable = () -> {
            clientCoreService.ConnectToServer();
        };
        new Thread(mainRunnable, "ClientAuthorizationConnectToServer").start();
    }

    public void WaitingAuthorizationResponse() {
        WaitingAuthorizationResponse waitingAuthorizationResponse = new WaitingAuthorizationResponse();
        waitingAuthorizationResponse.waitResponse();
    }

    public void HideAuthorizationStage(){
        Stage stage = GetStage(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
        stage.hide();
    }

//    public String GetTextFromChatMessageTextField(){
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
//        ChatControllerClientPresentation controller = fxmlLoader.getController();
//        return controller.GetTextFromChatMessageTextField();
//    }


    public boolean isAuthorizationAvailable() {
        return IsAuthorizationAvailable;
    }

    public void setAuthorizationAvailable(boolean authorizationAvailable) {
        IsAuthorizationAvailable = authorizationAvailable;
    }
}
