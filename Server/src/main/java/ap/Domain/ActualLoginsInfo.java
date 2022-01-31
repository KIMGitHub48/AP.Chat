package ap.Domain;

import java.net.Socket;
import java.util.ArrayList;

//TODO Разобраться с синхронизацией

public class ActualLoginsInfo {
    private ArrayList<String> actualLogins = new ArrayList<>();
    private ArrayList<Socket> actualSockets = new ArrayList<>();

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
                    compareSocketList.add(actualSockets.get(j));
                    break;
                }
            }
        }
        return compareSocketList;
    }
}
