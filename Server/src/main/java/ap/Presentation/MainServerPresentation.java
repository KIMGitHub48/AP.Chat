package ap.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ap.Presentation.Controllers.*;

import java.io.IOException;

/**
    Не является точкой входа в приложение.
 */
public class MainServerPresentation extends Application {
    public static MainServerPresentation mainServerPresentation;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public MainServerPresentation(){
        mainServerPresentation = this;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            System.exit(0);
            // Save file
        });//Закрывает все потоки при выходе
    }

    public static void main(String[] args) {
        launch();
    }

    private void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(MainServerPresentation.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void AddSystemMessage(String text){
        ChatControllerServerPresentation controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void AddTextToStartServerButtonAfterStart(){
        ChatControllerServerPresentation controller = fxmlLoader.getController();
        controller.AddTextToStartServerButtonAfterStart();
    }

    public void AddTextToStartServerButtonAfterStop(){
        ChatControllerServerPresentation controller = fxmlLoader.getController();
        controller.AddTextToStartServerButtonAfterStop();
    }

}