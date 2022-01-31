package ap.Domain;

import java.net.Socket;
import java.util.ArrayList;

//TODO Разобраться с синхронизацией

public class ActualLoginsInfo {
    private ArrayList<String> actualLogins;
    private ArrayList<Socket> actualSockets;

    public void AddActualInfo(String login, Socket socket) {
        actualLogins.add(login);
        actualSockets.add(socket);
    }

    public ArrayList<Socket> CompareActualLoginsWithMeta(ArrayList<String> loginsFromMeta){
        ArrayList<Socket> compareSocketList = new ArrayList<>();
        for (int i=0; i<loginsFromMeta.size(); i++){
            for (int j=0; j<actualLogins.size(); j++){
                boolean isEquals = loginsFromMeta.get(i).equals(actualLogins.get(j));
                if (isEquals){
                    compareSocketList.add(actualSockets.get(i));
                    break;
                }
            }
        }
        return compareSocketList;
    }
}
