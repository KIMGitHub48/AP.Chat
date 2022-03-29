package apClientAuthorization;

public class LauncherClientAuthorization {
    public static void main(String[] args) {
        Runnable mainRunnable = () -> {
            MainClientAuthorization.launch();
        };
        new Thread(mainRunnable, "ClientAuthorizationThread").start();
    }
}
