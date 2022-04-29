import apCommon.apModuleServices.ServerService.ServerUIService;
import apServerUI.ServerUIServiceImp;

module apServerUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires apCommon;

    exports apServerUI;

    //uses ServerService;

    provides ServerUIService with ServerUIServiceImp;
}
