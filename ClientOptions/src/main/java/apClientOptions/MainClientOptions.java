package apClientOptions;

import apClientOptions.Controllers.OptionsController;
import apCommon.ApFinals;
import apCommon.apModuleServices.ClientCoreService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainClientOptions extends Application {
    public static MainClientOptions mainClientOptionsRef;
    private ArrayList<Stage> listStage = new ArrayList<>();
    private ArrayList<String> listFileName = new ArrayList<>();
    private ArrayList<FXMLLoader> listFxmlLoader = new ArrayList<>();
    private ClientCoreService clientCoreService = apCommon.apModuleServices.ClientCoreService.getFirst();

    public MainClientOptions() {
        mainClientOptionsRef = this;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoadStage(true, ApFinals.FXML_OPTIONS_FILE_NAME);
    }

    public void ConnectToServer() {
        clientCoreService.ConnectToServer();
    }

    ;

    private void LoadStage(Boolean showOnStart, String fxmlName) {
        Scene scene;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(MainClientOptions.class.getResource(fxmlName));
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
            if (allStageCloseFlag) {
                System.out.println("Stage is closing");
                System.exit(0);
            }
        });
    }//Событие закрытия окна проверяет есть ли хоть одно открытое окно иначе закрывает все потоки и приложение.

    public void ShowOptionsStage() {
        Stage optionsStage = GetStage(ApFinals.FXML_OPTIONS_FILE_NAME);
        optionsStage.show();
    }

    private FXMLLoader GetFXMLLoader(String fileName) {
        for (int i = 0; i < listFileName.size(); i++) {
            if (fileName.equals(listFileName.get(i))) {
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

    public String GetServerIP() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_OPTIONS_FILE_NAME);
        OptionsController optionsController = fxmlLoader.getController();
        return optionsController.GetServerIP();
    }

    public Integer GetServerPort() {
        FXMLLoader fxmlLoader = GetFXMLLoader(ApFinals.FXML_OPTIONS_FILE_NAME);
        OptionsController optionsController = fxmlLoader.getController();
        return optionsController.GetServerPort();
    }
}
