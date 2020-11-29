package controllers.observers;

import model.Client;

public interface IClientObserver {
    void onClientAdded(Client client);
    void onClientDeleted(Client client);
    void onClientEdited(Client client);
}
