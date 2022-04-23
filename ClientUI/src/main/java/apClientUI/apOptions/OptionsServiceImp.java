package apClientUI.apOptions;
import apClientUI.MainClientUIService;
import apClientUI.OptionsService;
import apClientUI.apOptions.Controllers.OptionsController;
import apCommon.ApFinals;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class OptionsServiceImp implements OptionsService {
    static private ArrayList<Stage> listStage = new ArrayList<>();
    static private ArrayList<String> listFileName = new ArrayList<>();
    static private ArrayList<FXMLLoader> listFxmlLoader = new ArrayList<>();
    private MainClientUIService mainClientUIService = MainClientUIService.getFirst();

    @Override
    public void LoadStage() {
        LoadStage(ApFinals.FXML_OPTIONS_FILE_NAME);
    }

    @Override
    public void ShowOptionsStage() {
        GetOptionsStage().show();
    }

    @Override
    public void HideOptionsStage() {
        GetOptionsStage().hide();
    }

    @Override
    public String GetServerIP() {
        return GetOptionsController().GetServerIP();
    }

    @Override
    public Integer GetServerPort() {
        return GetOptionsController().GetServerPort();
    }

//    @Override
//    public String GetLogin() {
//        return null;
//    }
//
//    @Override
//    public String GetPassword() {
//        return null;
//    }


    //Важно при копировании изменить имя класса.
    //fxmlLoader = new FXMLLoader(КЛАСС ВМЕСТЕ С fxml ФАЙЛОМ.class.getResource(fxmlName));
    private void LoadStage(String fxmlName) {
        Scene scene;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(OptionsServiceImp.class.getResource(fxmlName));
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

    private Stage GetOptionsStage(){
        return GetStage(ApFinals.FXML_OPTIONS_FILE_NAME);
    }

    private OptionsController GetOptionsController(){
        return GetFXMLLoader(ApFinals.FXML_OPTIONS_FILE_NAME).getController();
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
