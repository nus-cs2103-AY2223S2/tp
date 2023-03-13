package mycelium.mycelium.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;


/**
 * The API for operations related to clients.
 */
public interface ClientModel {

    /**
     * Determines whether a given client is present in the list of clients.
     * @param client The client to check for existence.
     * @return true if the client is present in the list, false otherwise.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the specified client from the list of clients.
     * @param target The client to delete.
     */
    void deleteClient(Client target);

    /**
     * Adds a new client to the list of clients.
     * @param client The client to add.
     */
    void addClient(Client client);

    /**
     * Returns an observable list of clients that meet the specified filter predicate.
     * @return An observable list of clients that meet the specified filter predicate.
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter predicate used to generate the observable list of clients.
     * @param predicate The new filter predicate.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

}
