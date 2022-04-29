package apClientUI.apAuthorization;


import apClientUI.MainClientUI;
import apClientUI.MainClientUIService;
import apCommon.ApFinals;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationServiceImp implements apClientUI.AuthorizationService {
    static private ArrayList<Stage> listStage = new ArrayList<>();
    static private ArrayList<String> listFileName = new ArrayList<>();
    static private ArrayList<FXMLLoader> listFxmlLoader = new ArrayList<>();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();
    //TODO Реализовать методы

    @Override
    public void LoadStage(){
        LoadStage(ApFinals.FXML_AUTHORIZATION_FILE_NAME);
    }

    @Override
    public void SetButtonEnterTooltipTextAndShow(String text) {
        Platform.runLater(() -> GetAuthorizationController().SetButtonEnterTooltipTextAndShow(text));
    }

    @Override
    public void HideAuthorizationStage() {
        Platform.runLater(() -> GetAuthorizationStage().hide());
    }

    @Override
    public void ShowAuthorizationStage() {
        Platform.runLater(() -> GetAuthorizationStage().show());
    }

    @Override
    public String GetButtonEnterText() {
        return GetAuthorizationController().GetButtonEnterText();
    }

    @Override
    public void ChangeAuthorizationButtonEnterTextAndDisable(String text, boolean  disable) {
        Platform.runLater(() -> GetAuthorizationController().ChangeButtonEnterTextAndDisable(text, disable));
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
    public String GetLoginFromTextField() {
        return GetAuthorizationController().GetLoginFromTextField();
    }

    @Override
    public String GetPasswordFromTextField() {
        return GetAuthorizationController().GetPasswordFromTextField();
    }

    @Override
    public void AuthorizationResponseActionTimeError() {
        mainClientUIService.setAuthorizationAvailable(false);
        SetButtonEnterTooltipTextAndShow(ApFinals.SERVER_AUTHORIZATION_TIME_ERROR);
        ChangeAuthorizationButtonEnterTextAndDisable(ApFinals.ENTER, false);
    }

    //Важно при копировании изменить имя класса.
    //fxmlLoader = new FXMLLoader(КЛАСС ВМЕСТЕ С fxml ФАЙЛОМ.class.getResource(fxmlName));
    private void LoadStage(String fxmlName) {
        Scene scene;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(AuthorizationServiceImp.class.getResource(fxmlName));
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
}
