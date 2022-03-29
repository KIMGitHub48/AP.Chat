package apClientCore.Domain;


import apCommon.ApMessage;

public class FacadeClientDomain {
    public static void ConnectToServer() {
        MainClientDomain.mainDomainRef.ConnectToServer();
    }

    public static void SortOutMessageInThread(ApMessage apMessage) {
        MainClientDomain.mainDomainRef.SortOutMessageInThread(apMessage);
    }

    public static boolean IsConnected() {
        return MainClientDomain.mainDomainRef.IsConnected();
    }

    public static boolean isAuthorizationAvailable() {
        return MainClientDomain.mainDomainRef.isAuthorizationAvailable();
    }

    public static void setAuthorizationAvailable(boolean authorizationAvailable) {
        MainClientDomain.mainDomainRef.setAuthorizationAvailable(authorizationAvailable);
    }

    public static void WaitingAuthorizationResponse() {
        MainClientDomain.mainDomainRef.WaitingAuthorizationResponse();
    }

    public static String GetLogin() {
        return MainClientDomain.mainDomainRef.getLogin();
    }

    public static String GetPassword() {
        return MainClientDomain.mainDomainRef.getPassword();
    }
}
