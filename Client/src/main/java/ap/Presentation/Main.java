package ap.Presentation;

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
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public Main(){
        presentationMainRef = this;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("Primary"), 1024, 768);
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

    public String GetIPFromTextField(){
        PrimaryController controller = fxmlLoader.getController();
        return controller.GetIPFromTextField();
    }

    public Integer GetPortFromTextField(){
        PrimaryController controller = fxmlLoader.getController();
        return controller.GetPortFromTextField();
    }

    public void AddSystemMessage(String text){
        PrimaryController controller = fxmlLoader.getController();
        controller.SetSystemText(text);
    }

    public void chatChannelMessage(String channelMessage, String channelName) {
        System.out.println("Пришло сообщение");
        PrimaryController controller = fxmlLoader.getController();
        controller.setChatChannelMessage(channelMessage,channelName);
    }
}