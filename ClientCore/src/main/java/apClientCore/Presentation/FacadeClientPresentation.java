package apClientCore.Presentation;
/*
Этот класс реализует паттерн программирования Фасад, все общение между этим пакетом и остальными должно быть реализованно через этот класс.
 */


import apCommon.ApMessage;

public class FacadeClientPresentation {
    public static void Launcher(String[] args){
        Runnable mainRunnable = () -> {
            MainClientPresentation.main(args);
        };
        new Thread(mainRunnable, "UIThread").start();
    }//Запускает UI в новом потоке.
    public static void AddSystemMessage(String text){
        MainClientPresentation.mainPresentationRef.AddSystemMessage(text);
    }
    public static String GetIPFromTextField(){
        return MainClientPresentation.mainPresentationRef.GetIPFromTextField();
    }
    public static Integer GetPortFromTextField(){
        return MainClientPresentation.mainPresentationRef.GetPortFromTextField();
    }

    public static void SetButtonEnterTooltipTextAndShow(String text){
        MainClientPresentation.mainPresentationRef.SetButtonEnterTooltipTextAndShow(text);
    }

    public static void chatChannelMessage(ApMessage apMessage) {
        MainClientPresentation.mainPresentationRef.chatChannelMessage(apMessage);
    }
}