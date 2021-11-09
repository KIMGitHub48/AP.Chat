package org.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;

/**
    Не является точкой входа в приложение.
 */
public class Main extends Application {
    public static Main presentationMain;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public Main(){
        presentationMain = this;
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
        fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void AddSystemMessage(String text){
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void AddTextToStartServerButtonAfterStart(){
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        controller.AddTextToStartServerButtonAfterStart();
    }

    public void AddTextToStartServerButtonAfterStop(){
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        controller.AddTextToStartServerButtonAfterStop();
    }

}