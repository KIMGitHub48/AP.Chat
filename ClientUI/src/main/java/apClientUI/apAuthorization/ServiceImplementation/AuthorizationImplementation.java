package apClientUI.apAuthorization.ServiceImplementation;


import apClientUI.MainClientUI;
import apClientUI.MainClientUIService;
import apCommon.ApFinals;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationImplementation implements apClientUI.AuthorizationService {
    private ArrayList<Stage> listStage = new ArrayList<>();
    private ArrayList<String> listFileName = new ArrayList<>();
    private ArrayList<FXMLLoader> listFxmlLoader = new ArrayList<>();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();
    //TODO Реализовать методы

    @Override
    public void Launcher(){
        ShowAuthorizationStage();
    }

    public AuthorizationImplementation(){
        LoadStage(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
    }

    @Override
    public void SetButtonEnterTooltipTextAndShow(String text) {
        GetAuthorizationController().SetButtonEnterTooltipTextAndShow(text);
    }

    @Override
    public void HideAuthorizationStage() {
        GetAuthorizationStage().hide();
    }

    @Override
    public void ShowAuthorizationStage() {
        GetAuthorizationStage().show();
    }

    @Override
    public String GetButtonEnterText() {
        return GetAuthorizationController().GetButtonEnterText();
    }

    @Override
    public void ChangeAuthorizationButtonEnterTextAndDisable(String text, boolean  disable) {
        GetAuthorizationController().ChangeButtonEnterTextAndDisable(text, disable);
    }

    @Override
    public void AuthorizationResponseActionConnectionError() {
        SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_CONNECT_ERROR);
        ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER, false);
    }

    @Override
    public void AuthorizationResponseActionNotPassed() {
        SetButtonEnterTooltipTextAndShow(ApFinals.AUTHORIZATION_NOT_PASSED);
        ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER, false);
    }

    @Override
    public void AuthorizationResponseActionPassed() {
        //TODO сцена после авторизации clientCoreService.ShowHideChatStage(true);
        HideAuthorizationStage();
        ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER, false);
    }

    @Override
    public void AuthorizationResponseActionTimeError() {
        mainClientUIService.setAuthorizationAvailable(false);
        SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_AUTHORIZATION_TIME_ERROR);
        ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER, false);
    }

    private void LoadStage(String fxmlName) {
        Scene scene;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(MainClientUI.class.getResource(ApFinals.FXML_AUTHORIZATION_FILE_NAME_PATH+fxmlName));
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            listStage.add(stage);
            listFileName.add(fxmlName);
            listFxmlLoader.add(fxmlLoader);
            //TODO Подписка на закрытие setCloseStageEvent(stage);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка загрузки сцены");
        }
    }

//    public String GetButtonEnterText() {
//        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
//        LoginPasswordControllerClientPresentation controller = fxmlLoader.getController();
//        return controller.GetButtonEnterText();
//    }

    private Stage GetAuthorizationStage(){
        return GetStage(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
    }

    private apClientUI.apAuthorization.Controllers.AuthorizationController GetAuthorizationController(){
        return GetFXMLLoader(ApFinals.FXML_AUTHORIZATION_FILE_NAME).getController();
    }

    private Stage GetStage(String fileName){
        for (int i=0;i<listFileName.size();i++){
            if (fileName.equals(listFileName.get(i))){
                return listStage.get(i);
            }
        }
        return null;
    }

    private FXMLLoader GetFXMLLoader(String fileName){
        for (int i=0;i<listFileName.size();i++){
            if (fileName.equals(listFileName.get(i))){
                return listFxmlLoader.get(i);
            }
        }
        return null;
    }

//    private void ShowHideStage(String fileName, boolean show) {
//        Stage stage;
//        for (int i = 0; i < listStage.size(); i++) {
//            if (fileName.equals(listFileName.get(i))) {
//                if (show){
//                    stage = listStage.get(i);
//                    Platform.runLater(stage::show);
//                } else
//                {
//                    stage = listStage.get(i);
//                    Platform.runLater(stage::hide);
//                }
//            }
//        }
//    }
}
