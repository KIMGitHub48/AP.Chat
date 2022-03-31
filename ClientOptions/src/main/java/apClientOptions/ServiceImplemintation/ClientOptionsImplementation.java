package apClientOptions.ServiceImplemintation;
import apClientOptions.MainClientOptions;

public class ClientOptionsImplementation implements apCommon.apModuleServices.ClientOptionsService {
    private MainClientOptions main = MainClientOptions.mainClientOptionsRef;
    public void launcher(String[] args) {
        Runnable mainRunnable = () -> {
            MainClientOptions.main(args);
        };
        new Thread(mainRunnable, "ClientOptionsThread").start();
    }

    @Override
    public void ShowOptionsStage() {
        main.ShowOptionsStage();
    }

    @Override
    public String GetServerIP() {
        return main.GetServerIP();
    }

    @Override
    public Integer GetServerPort() {
        return main.GetServerPort();
    }
}
