package mycelium.mycelium.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueList<Project> projects;
    private final UniqueList<Client> clients;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        projects = new UniqueList<>();
        clients = new UniqueList<>();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setClients(List<Client> clients) {
        this.clients.setItems(clients);
    }

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setItems(projects);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setProjects(newData.getProjectList());
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients and "
                + projects.asUnmodifiableObservableList().size() + " projects";
        // TODO: refine later
    }

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     * @param client must not be null.
     * @return true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        return clients.contains(client);
    }

    /**
     * Adds a client to the address book.
     * @param client must not be null.
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Replaces the given client {@code client} in the list with {@code editedClient}.
     * @param client must exist in the address book.
     * @param editedClient must not be the same as another existing client in the address book.
     */
    public void setClient(Client client, Client editedClient) {
        clients.setItem(client, editedClient);
    }

    /**
     * Removes {@code client} from this {@code AddressBook}.
     * @param client must exist in the address book.
     */
    public void removeClient(Client client) {
        clients.remove(client);
    }

    /**
     * Returns an unmodifiable view of the clients list.
     * @return an unmodifiable view of the clients list.
     */
    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     * @param project must not be null.
     * @return true if a project with the same identity as {@code project} exists in the address book.
     */
    public boolean hasProject(Project project) {
        return projects.contains(project);
    }

    /**
     * Removes the project if the project has the same name as the {@code project} in the address book.
     * @param project must not be null.
     */
    public void removeProject(Project project) {
        projects.remove(project);
    }

    /**
     * Adds a project to Mycelium if it does not contain an existing project with the same name.
     * @param project must not be null.
     */
    public void addProject(Project project) {
        projects.add(project);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     */
    public void setProject(Project target, Project editedProject) {
        // NOTE: no need for null checks at this stage, let the UniqueList handle it
        projects.setItem(target, editedProject);
    }
    @Override
    public ObservableList<Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressBook that = (AddressBook) o;
        return Objects.equals(projects, that.projects) && Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projects, clients);
    }
}
