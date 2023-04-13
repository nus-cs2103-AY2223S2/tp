package mycelium.mycelium.model;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;


/**
 * The API for operations related to clients.
 */
public interface ClientModel {

    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;
    /**
     * Finds a single client that matches the specified predicate. Expects to
     * find either zero or one clients. If more than one client matches the
     * provided predicate, throws {@code DuplicateClientException}.
     */
    Optional<Client> getUniqueClient(Predicate<Client> predicate);

    /**
     * Determines whether a given client is present in the list of clients.
     *
     * @param client The client to check for existence.
     * @return true if the client is present in the list, false otherwise.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the specified client from the list of clients.
     *
     * @param target The client to delete.
     */
    void deleteClient(Client target);

    /**
     * Adds a new client to the list of clients.
     *
     * @param client The client to add.
     */
    void addClient(Client client);

    /**
     * Returns an observable list of clients that meet the specified filter predicate.
     *
     * @return An observable list of clients that meet the specified filter predicate.
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Updates the filter predicate used to generate the observable list of clients.
     *
     * @param predicate The new filter predicate.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * @param target client to be replaced
     * @param editedClient client to replace with
     */
    void setClient(Client target, Client editedClient);

}
