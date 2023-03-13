package mycelium.mycelium.model;

import static java.util.Objects.requireNonNull;
import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import mycelium.mycelium.commons.core.GuiSettings;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.exceptions.DuplicateClientException;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.exceptions.DuplicateProjectException;
import mycelium.mycelium.model.util.exceptions.DuplicateItemException;
import mycelium.mycelium.model.util.exceptions.ItemNotFoundException;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Project> filteredProjects;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        filteredProjects = new FilteredList<>(this.addressBook.getProjectList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public Optional<Client> getUniqueClient(Predicate<Client> predicate) {
        List<Client> clients = addressBook.getClientList().stream().filter(predicate).collect(Collectors.toList());
        if (clients.size() > 1) {
            throw new DuplicateClientException();
        }
        return clients.size() == 0 ? Optional.empty() : Optional.of(clients.get(0));
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client client) {
        try {
            addressBook.removeClient(client);
        } catch (ItemNotFoundException e) {
            logger.warning(String.format(
                "Requested deletion for client with name %s not found in address book, ignoring...",
                client.getName()));
        }
    }

    @Override
    public void addClient(Client client) {
        try {
            addressBook.addClient(client);
        } catch (DuplicateItemException e) {
            throw new DuplicateClientException();
        }
        updateFilteredClientList(x -> true);
    }

    //=========== Filtered Client List Accessors =============================================================

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public Optional<Project> getUniqueProject(Predicate<Project> predicate) {
        List<Project> projects = addressBook.getProjectList().stream().filter(predicate).collect(Collectors.toList());
        if (projects.size() > 1) {
            throw new DuplicateProjectException();
        }
        return projects.size() == 0 ? Optional.empty() : Optional.of(projects.get(0));
    }

    public boolean hasProject(Project project) {
        return addressBook.hasProject(project);
    }

    @Override
    public void deleteProject(Project project) {
        try {
            addressBook.removeProject(project);
        } catch (ItemNotFoundException e) {
            logger.warning(String.format(
                "Requested deletion for project with name %s not found in address book, ignoring...",
                project.getName()));
        }
    }

    @Override
    public void addProject(Project project) {
        try {
            addressBook.addProject(project);
        } catch (DuplicateItemException e) {
            throw new DuplicateProjectException();
        }
        updateFilteredProjectList(x -> true);
    }

    //=========== Filtered Project List Accessors =============================================================

    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelManager that = (ModelManager) o;
        return Objects.equals(addressBook, that.addressBook)
            && Objects.equals(userPrefs, that.userPrefs)
            && Objects.equals(filteredPersons, that.filteredPersons)
            && Objects.equals(filteredClients, that.filteredClients)
            && Objects.equals(filteredProjects, that.filteredProjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressBook, userPrefs, filteredPersons, filteredClients, filteredProjects);
    }


}
