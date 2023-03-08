package mycelium.mycelium.model;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;

import java.util.function.Predicate;

/**
 * The API for operations related to clients.
 */
public interface ClientModel {

    public boolean hasClient(Client client);
    public void deleteClient(Client target);
    public void addClient(Client client);
    public ObservableList<Client> getFilteredClientList();
    public void updateFilteredClientList(Predicate<Client> predicate);

}
