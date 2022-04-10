//package apClientCore.Core.ServiceImplementation;
//
//import apClientCore.Core.MainClientCoreService;
//import apClientCore.Core.Net.SortOutMessageInThread;
//import apCommon.ApMessage;
//import apCommon.apModuleServices.ClientCoreService;
//
//public class ClientCoreServiceImplementations implements ClientCoreService {
//    private MainClientCoreService mainClientCoreService = MainClientCoreService.getFirst();
//
//    @Override
//    public boolean IsConnected() {
//        boolean isConnected = mainClientCoreService.IsConnected();
//        return isConnected;
//    }
//
//    @Override
//    public void ConnectToServer() {
//        mainClientCoreService.ConnectToServer();
//    }
//
//    @Override
//    public void SortOutMessageInThread(ApMessage apMessage) {
//        apClientCore.Core.Net.SortOutMessageInThread sortOutMessageInThread = new SortOutMessageInThread(apMessage);
//        sortOutMessageInThread.start();
//    }
//
//    @Override
//    public void setLogin(String login) {
//        mainClientCoreService.setLogin(login);
//    }
//
//    @Override
//    public void setPassword(String password) {
//        mainClientCoreService.setPassword(password);
//    }
//
//    @Override
//    public boolean isAuthorizationAvailable() {
//        return false;
//    }
//
//    @Override
//    public void AuthorizationButtonEnterInThread() {
//
//    }
//
//    @Override
//    public void setAuthorizationAvailable(boolean authorizationAvailable) {
//
//    }
//
//    @Override
//    public void ShowHideChatStage(boolean show) {
//        //
//    }
//}
