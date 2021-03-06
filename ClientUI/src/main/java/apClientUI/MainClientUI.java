package apClientUI;

import apCommon.apModuleServices.ClientService.ClientCoreService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainClientUI extends Application {

    private ArrayList<Stage> listStage = new ArrayList<>();
    private ClientCoreService clientCoreService = ClientCoreService.getFirst();
    private AuthorizationService authorizationService = AuthorizationService.getFirst();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();
    private OptionsService optionsService = OptionsService.getFirst();

    @Override
    public void start(Stage stage) throws Exception {
        authorizationService.LoadStage();
        optionsService.LoadStage();
        authorizationService.ShowAuthorizationStage();
    }

    public void Launcher(String[] args) {
        launch();
    }

//    @Override
//    public boolean IsConnected() {
//        return clientCoreService.IsConnected();
//    }

//    @Override
//    public void ConnectToServer() {
//        Runnable mainRunnable = () -> {
//            clientCoreService.ConnectToServer();
//        };
//        new Thread(mainRunnable, "ClientAuthorizationConnectToServer").start();
//    }

//    @Override
//    public boolean isAuthorizationAvailable() {
//        return clientCoreService.isAuthorizationAvailable();
//    }

//    @Override
//    public void AuthorizationButtonEnterInThread() {
//        clientCoreService.AuthorizationButtonEnterInThread();
//    }

//    @Override
//    public void setAuthorizationAvailable(boolean authorizationAvailable) {
//        clientCoreService.setAuthorizationAvailable(authorizationAvailable);
//        //IsAuthorizationAvailable = authorizationAvailable;
//    }

//    @Override
//    public void SendMessage(ApMessage apMessage) {
//        clientCoreService.SendMessage(apMessage);
//    }

//    @Override
//    public void AuthorizationResponseActionConnectionError() {
//        authorizationService.AuthorizationResponseActionConnectionError();
//    }

//    @Override
//    public void AuthorizationResponseActionTimeError() {
//        authorizationService.AuthorizationResponseActionTimeError();
//    }

//    @Override
//    public void AuthorizationResponseActionNotPassed() {
//        authorizationService.AuthorizationResponseActionNotPassed();
//    }

//    @Override
//    public void AuthorizationResponseActionPassed() {
//        authorizationService.AuthorizationResponseActionPassed();
//    }

//    @Override
//    public String GetServerIP() {
//        return null;
//    }
//
//    @Override
//    public Integer GetServerPort() {
//        return null;
//    }


//    private void LoadStage(Boolean showOnStart, String fxmlName) {
//        Scene scene;
//        Stage stage = new Stage();
//        FXMLLoader fxmlLoader;
//        try {
//            fxmlLoader = new FXMLLoader(MainClientAuthorization.class.getResource(fxmlName));
//            scene = new Scene(fxmlLoader.load());
//            stage.setScene(scene);
//            listStage.add(stage);
//            listFileName.add(fxmlName);
//            listFxmlLoader.add(fxmlLoader);
//            if (showOnStart == true) {
//                stage.show();
//            }
//            setCloseStageEvent(stage);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("???????????? ???????????????? ??????????");
//        }
//    }

    //TODO ?????? ?????????????????? ???????????????????
    private void setCloseStageEvent(Stage stage) {
        stage.setOnHidden(event -> {
            boolean allStageCloseFlag = true;
            for (int i = 0; i < listStage.size(); i++) {
                if (listStage.get(i).isShowing()) {
                    listStage.get(i).show();
                    allStageCloseFlag = false;
                    break;
                } else {
                    System.out.println("?????????? ???? ??????????");
                }
            }
            if (allStageCloseFlag) {
                System.out.println("Stage is closing");
                System.exit(0);
            }
        });
    }//?????????????? ???????????????? ???????? ?????????????????? ???????? ???? ???????? ???????? ???????????????? ???????? ?????????? ?????????????????? ?????? ???????????? ?? ????????????????????.
//
//    public String GetLoginFromTextField() {
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        AuthorizationController authorizationController = fxmlLoader.getController();
//        return authorizationController.GetLoginFromTextField();
//    }
//
//    public String GetPasswordFromTextField() {
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        AuthorizationController authorizationController = fxmlLoader.getController();
//        return authorizationController.GetPasswordFromTextField();
//    }

//    public String GetButtonEnterText() {
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        AuthorizationController authorizationController = fxmlLoader.getController();
//        return authorizationController.GetButtonEnterText();
//    }

//    public void AddSystemMessage(String text) {
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
//        ChatControllerClientPresentation controller = fxmlLoader.getController();
//        controller.SetSystemText(text);
//    }

//    public void chatChannelMessage(ApMessage apMessage) {
//        System.out.println("???????????? ??????????????????");
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
//        ChatControllerClientPresentation controller = fxmlLoader.getController();
//        controller.setChatChannelMessage(apMessage.getChatChannelText(), apMessage.getChatChannelName());
//    }

//    public void SetButtonEnterTooltipTextAndShow(String text){
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        AuthorizationController authorizationController = fxmlLoader.getController();
//        authorizationController.SetButtonEnterTooltipTextAndShow(text);
//    }

//    public void ChangeButtonEnterTextAndDisable(String text, boolean disable) {
//        AuthorizationController authorizationController = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME).getController();
//        authorizationController.ChangeButtonEnterTextAndDisable(text, disable);
//    }

//    private FXMLLoader GetFXMLLoader(String fileName){
//        for (int i=0;i<listFileName.size();i++){
//            if (fileName.equals(listFileName.get(i))){
//                return listFxmlLoader.get(i);
//            }
//        }
//        return null;
//    }

//    private Stage GetStage(String fileName) {
//        for (int i = 0; i < listFileName.size(); i++) {
//            if (fileName.equals(listFileName.get(i))) {
//                return listStage.get(i);
//            }
//        }
//        return null;
//    }
//    public void ShowOptionsStage(){
//        clientCoreMapService.ShowOptionsStage();
//    }


//    public void ChangeAuthorizationButtonEnterTextAndDisable(String text, boolean disable){
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        AuthorizationController authorizationController = fxmlLoader.getController();
//        authorizationController.ChangeButtonEnterTextAndDisable(text, disable);
//    }

//    public void WaitingAuthorizationResponse() {
//        WaitingAuthorizationResponse waitingAuthorizationResponse = new WaitingAuthorizationResponse();
//        waitingAuthorizationResponse.waitResponse();
//    }

//    public void HideAuthorizationStage(){
//        Stage stage = GetStage(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        stage.hide();
//    }

//    public String GetTextFromChatMessageTextField(){
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
//        ChatControllerClientPresentation controller = fxmlLoader.getController();
//        return controller.GetTextFromChatMessageTextField();
//    }
}
