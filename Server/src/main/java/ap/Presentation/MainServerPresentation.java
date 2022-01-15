package ap.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ap.Presentation.Controllers.*;

import java.io.IOException;
import java.util.List;

/**
    Не является точкой входа в приложение.
 */
public class MainServerPresentation extends Application {
    public static MainServerPresentation mainServerPresentation;
    private FXMLLoader fxmlLoader;
    private List<Stage> listStage;
    private List<String> listName;

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
            if (showOnStart == true){
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка загрузки сцены");
        }
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