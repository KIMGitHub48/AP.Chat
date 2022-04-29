import apCommon.apModuleServices.ServerService.*;
import apServerDATA.ServerDATAServiceImp;

module apServerDATA {
    requires reflections;
    requires java.persistence;
    requires java.sql;
    requires apCommon;

    exports apServerDATA;

    //uses ServerService;

    provides ServerDATAService with ServerDATAServiceImp;
}
