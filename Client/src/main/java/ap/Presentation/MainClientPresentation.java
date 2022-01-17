/*
setShowHideEvent(Stage stage) - Метод следит за открытыми окнами, если все окна закрыты завершает программу,
    метод необходим для закрытия потоков работающих отдельно от окон.
* */
package ap.Presentation;

import ap.Presentation.Controllers.ChatControllerClientPresentation;
import ap.Presentation.Controllers.LoginPasswordControllerClientPresentation;
import ap.Presentation.Controllers.OptionsControllerClientPresentation;
import ap.common.ApFinals;
import javafx.application.Application;
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
    private Scene scene;
    //private FXMLLoader fxmlLoader;
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
//        primaryStage.setOnCloseRequest(event -> {
//            System.out.println("Stage is closing");
//            System.exit(0);
        // Save file
//        });//Закрывает все потоки при выходе
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
        });//Закрывает все потоки при выходе
    }

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

    public void AddSystemMessage(String text) {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
        ChatControllerClientPresentation controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void chatChannelMessage(String channelMessage, String channelName) {
        System.out.println("Пришло сообщение");
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_CHAT_FILE_NAME);
        ChatControllerClientPresentation controller = fxmlLoader.getController();
        controller.setChatChannelMessage(channelMessage, channelName);
    }

    public void SetButtonEnterTooltipTextAndShow(String text){
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_LOGIN_PASSWORD_FILE_NAME);
        LoginPasswordControllerClientPresentation controller = fxmlLoader.getController();
        controller.SetButtonEnterTooltipTextAndShow(text);
    }

    private FXMLLoader GetFXMLLoader(String fileName){
        for (int i=0;i<listFileName.size();i++){
            if (fileName.equals(listFileName.get(i))){
                return listFxmlLoader.get(i);
            }
        }
        return null;
    }

    public void ShowOptionsStage() {
        ShowHideStage(ApFinals.FXML_OPTIONS_FILE_NAME);
    }

    private void ShowHideStage(String fileName) {
        for (int i = 0; i < listStage.size(); i++) {
            if (fileName.equals(listFileName.get(i))) {
                listStage.get(i).show();
            }
        }
    }
}