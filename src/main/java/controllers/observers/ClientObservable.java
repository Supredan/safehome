package controllers.observers;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientObservable {
    private static List<IClientObserver> clientObservers = new ArrayList<>();

    public static void registerClientObserver(IClientObserver iClientObserver){
        clientObservers.add(iClientObserver);
    }

    public static void onClientAdded(Client client){
        for (IClientObserver iClientObserver : clientObservers) {
            iClientObserver.onClientAdded(client);
        }
    }

    public static void onClientDeleted(Client client){
        for (IClientObserver iClientObserver : clientObservers) {
            iClientObserver.onClientDeleted(client);
        }
    }

    public static void onClientEdit(Client client){
        for (IClientObserver iClientObserver : clientObservers) {
            iClientObserver.onClientEdited(client);
        }
    }
}
