package apClientAuthorization.ServiceImplemintation;
import apClientAuthorization.MainClientAuthorization;

public class ClientAuthorizationImplementation implements apCommon.apModuleServices.ClientAuthorizationService {
    public void launcher(String[] args) {
        Runnable mainRunnable = () -> {
            MainClientAuthorization.main(args);
        };
        new Thread(mainRunnable, "ClientAuthorizationThread").start();
    }
}
