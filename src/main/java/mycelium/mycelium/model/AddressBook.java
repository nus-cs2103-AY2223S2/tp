package mycelium.mycelium.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.person.UniquePersonList;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
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
        persons = new UniquePersonList();
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
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }
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

        setPersons(newData.getPersonList());
        setClients(newData.getClientList());
        setProjects(newData.getProjectList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    public boolean hasClient(Client client) {
        return clients.contains(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    public boolean hasProject(Project project) {
        return projects.contains(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public void addProject(Project project) {
        projects.add(project);
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
        return Objects.equals(persons, that.persons) && Objects.equals(projects, that.projects)
                && Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, projects, clients);
    }
}
