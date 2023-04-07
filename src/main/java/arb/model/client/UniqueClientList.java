package arb.model.client;

import static arb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import arb.model.client.exceptions.ClientNotFoundException;
import arb.model.client.exceptions.DuplicateClientException;
import arb.model.project.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of clients that enforces uniqueness between its elements and does not allow nulls.
 * A client is considered unique by comparing using {@code Client#isSameClient(Client)}. As such, adding and updating of
 * clients uses Client#isSameClient(Client) for equality so as to ensure that the client being added or updated is
 * unique in terms of identity in the UniqueClientList. However, the removal of a client uses Client#equals(Object) so
 * as to ensure that the client with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Client#isSameClient(Client)
 */
public class UniqueClientList implements Iterable<Client> {

    private final ObservableList<Client> internalList = FXCollections.observableArrayList();
    private final ObservableList<Client> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent client as the given argument.
     */
    public boolean contains(Client toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClient);
    }

    /**
     * Returns true if the list contains an equivalent client with {@code Name}.
     */
    public boolean contains(Name toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(c -> c.getName().equals(toCheck));
    }

    /**
     * Adds a client to the list.
     * The client must not already exist in the list.
     */
    public void add(Client toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedClient} must not be the same as another existing client in the list.
     */
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClientNotFoundException();
        }

        if (!target.isSameClient(editedClient) && contains(editedClient)) {
            throw new DuplicateClientException();
        }

        internalList.set(index, editedClient);
    }

    /**
     * Removes the equivalent client from the list.
     * The client must exist in the list.
     */
    public void remove(Client toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClientNotFoundException();
        }
    }

    public void setClients(UniqueClientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        requireAllNonNull(clients);
        if (!clientsAreUnique(clients)) {
            throw new DuplicateClientException();
        }

        internalList.setAll(clients);
    }

    /**
     * Unlinks all linked projects from the clients in the list.
     */
    public void resetProjectLinkings() {
        internalList.stream().forEach(c -> unlinkAllProjectsFromClient(c));
    }

    /**
     * Unlinks all linked projects from {@code client}.
     */
    private void unlinkAllProjectsFromClient(Client client) {
        requireNonNull(client);
        assert contains(client);
        client.unlinkAllProjects();
        setClient(client, client);
    }

    /**
     * Unlinks the linked client from {@code project}.
     */
    public void unlinkClientFromProject(Project project) {
        requireNonNull(project);
        project.getLinkedClient().ifPresent(c -> {
            c.unlinkProject(project);
            setClient(c, c);
        });
    }

    /**
     * Links {@code client} to {@code project}.
     */
    public void linkClientToProject(Client client, Project project) {
        requireAllNonNull(client, project);
        assert contains(client);
        client.linkProject(project);
        setClient(client, client);
    }

    /**
     * Links the client with {@code clientName} to {@code project}.
     */
    public void linkClientToProject(Name clientName, Project project) {
        assert contains(clientName);
        Iterator<Client> iterator = iterator();
        while (iterator.hasNext()) {
            Client toMatch = iterator.next();
            if (toMatch.getName().equals(clientName)) {
                toMatch.linkProject(project);
                project.linkToClient(toMatch);
                return;
            }
        }
    }

    /**
     * Transfers all clients linked to {@code original} to {@code target}.
     */
    public void transferLinkedClients(Project original, Project target) {
        requireAllNonNull(original, target);
        original.getLinkedClient().ifPresent(c -> {
            c.unlinkProject(original);
            c.linkProject(target);
            target.linkToClient(c);
            setClient(c, c);
        });
    }

    /**
     * Returns the number of clients in the list that fulfill {@code predicate}.
     */
    public int numberOfClientsMatchingPredicate(Predicate<Client> predicate) {
        return internalList.stream().filter(predicate).collect(Collectors.toList()).size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Client> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Client> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClientList // instanceof handles nulls
                        && internalList.equals(((UniqueClientList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code clients} contains only unique clients.
     */
    private boolean clientsAreUnique(List<Client> clients) {
        for (int i = 0; i < clients.size() - 1; i++) {
            for (int j = i + 1; j < clients.size(); j++) {
                if (clients.get(i).isSameClient(clients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
