package ap.Presentation;

import ap.Presentation.Net.InMassages.AuthorizationPresentationPart;
import ap.common.ApMessage;

import java.util.concurrent.TimeUnit;

public class GrabApMessageFromClientDomainInCycle extends Thread {
    @Override
    public void run(){
        while(true) {
            ApMessage apMessage = ap.Domain.FacadeClientDomain.GetPresentationApMessageFromList();
            if (apMessage != null) {
                switch (apMessage.getType()){
                    case authorization:
                        ap.Presentation.Net.InMassages.AuthorizationPresentationPart authorizationPresentationPart = new AuthorizationPresentationPart(apMessage);
                        authorizationPresentationPart.run();
                        break;
                }
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
