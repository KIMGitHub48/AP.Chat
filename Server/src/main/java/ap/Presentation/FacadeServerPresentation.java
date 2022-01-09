package ap.Presentation;
/*
Этот класс реализует паттерн программирования Фасад, все общение между этим пакетом и остальными должно быть реализованно через этот класс.
 */


public class FacadeServerPresentation {
    public static void Launcher(String[] args){
        Runnable mainRunnable = () -> {
            MainServerPresentation.main(args);
        };
        new Thread(mainRunnable, "UIThread").start();
    }//Запускает UI в новом потоке.

    public static void AddSystemMessage(String text){
        MainServerPresentation.mainServerPresentation.AddSystemMessage(text);
    }
    public static void AddTextToStartServerButtonAfterStart(){
        MainServerPresentation.mainServerPresentation.AddTextToStartServerButtonAfterStart();
    }
    public static void AddTextToStartServerButtonAfterStop(){
        MainServerPresentation.mainServerPresentation.AddTextToStartServerButtonAfterStop();
    }
}
