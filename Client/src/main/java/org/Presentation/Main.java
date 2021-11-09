package org.Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
    Не является точкой входа в приложение.
 */
public class Main extends Application {
    public static Main presentationMainRef;
    private static Scene scene;
    private static FXMLLoader fxmlLoader;

    public Main(){
        presentationMainRef = this;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public String GetIPFromTextField(){
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        return controller.GetIPFromTextField();
    }

    public Integer GetPortFromTextField(){
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        return controller.GetPortFromTextField();
    }

    public void AddSystemMessage(String text){
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void SetTextFieldChannelMessage(String channelMessage, String channelName) {
        org.Presentation.PrimaryController controller = fxmlLoader.getController();
        controller.setTextFieldChannelMessage(channelMessage,channelName);
    }
}