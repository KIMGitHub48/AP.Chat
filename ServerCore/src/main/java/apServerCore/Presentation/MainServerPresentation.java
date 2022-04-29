package apServerCore.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import apServerCore.Presentation.Controllers.*;

import java.io.IOException;
import java.util.ArrayList;

/**
    Не является точкой входа в приложение.
 */
public class MainServerPresentation extends Application {
    public static MainServerPresentation mainServerPresentation;
    private FXMLLoader fxmlLoader;
    private ArrayList<Stage> listStage = new ArrayList<>();
    private ArrayList<String> listName = new ArrayList<>();

    public MainServerPresentation(){
        mainServerPresentation = this;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        LoadStage(true,"Chat");
        LoadStage(true,"Main");
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            System.exit(0);
            // Save file
        });//Закрывает все потоки при выходе
    }

    public static void main(String[] args) {
        launch();
    }

    private void LoadStage(Boolean showOnStart,String fxmlName){
        Scene scene;
        Stage stage = new Stage();
        try {
            scene = new Scene(loadFXML(fxmlName));
            stage.setScene(scene);
            listStage.add(stage);
            listName.add(fxmlName);
            if (showOnStart == true){
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

    private Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(MainServerPresentation.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void AddSystemMessage(String text){
        MainControllerServerPresentation controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void AddTextToStartServerButtonAfterStart(){
        MainControllerServerPresentation controller = fxmlLoader.getController();
        controller.AddTextToStartServerButtonAfterStart();
    }

    public void AddTextToStartServerButtonAfterStop(){
        MainControllerServerPresentation controller = fxmlLoader.getController();
        controller.AddTextToStartServerButtonAfterStop();
    }

}