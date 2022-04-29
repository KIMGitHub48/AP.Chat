package apServerUI;

public class MainServerUIServiceImp implements MainServerUIService {
    private static MainServerUI mainServerUI;

    @Override
    public void Launcher(String[] args) {
        mainServerUI = new MainServerUI();
        mainServerUI.Launcher(args);
    }
}
