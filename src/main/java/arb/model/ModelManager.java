package arb.model;

import static arb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.logging.Logger;

import arb.commons.core.GuiSettings;
import arb.commons.core.LogsCenter;
import arb.model.client.Client;
import arb.model.project.Project;
import arb.model.tag.TagMapping;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final SortedList<Client> sortedClients;
    private final FilteredList<Project> filteredProjects;
    private final SortedList<Project> sortedProjects;
    private final ObservableList<TagMapping> tagMappings;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        sortedClients = new SortedList<>(this.filteredClients);
        filteredProjects = new FilteredList<>(this.addressBook.getProjectList());
        sortedProjects = new SortedList<>(this.filteredProjects);
        tagMappings = this.addressBook.getTagMappingList();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void resetProjectList() {
        logger.info("Resetting the project list");
        addressBook.resetProjectTagMappings();
        addressBook.resetClientLinkings(); // unlink clients
        addressBook.setProjects(new ArrayList<Project>());
    }

    @Override
    public void resetClientList() {
        logger.info("Resetting the client list");
        addressBook.resetClientTagMappings();
        addressBook.resetProjectLinkings(); // unlink projects
        addressBook.setClients(new ArrayList<Client>());
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return addressBook.hasProject(project);
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeClient(target);
    }

    @Override
    public void deleteProject(Project target) {
        addressBook.removeProject(target);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        resetFilteredAndSortedClientList();
    }

    @Override
    public void addProject(Project project) {
        addressBook.addProject(project);
        resetFilteredAndSortedProjectList();
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setClient(target, editedClient);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        addressBook.setProject(target, editedProject);
    }

    @Override
    public int numberOfClientsMatchingPredicate(Predicate<Client> predicate) {
        return addressBook.numberOfClientsMatchingPredicate(predicate);
    }

    @Override
    public void setProjectToLink(Project project) {
        requireNonNull(project);
        addressBook.setProjectToLink(project);
    }

    @Override
    public void resetProjectToLink() {
        addressBook.resetProjectToLink();
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void linkProjectToClient(Client client) {
        requireNonNull(client);
        addressBook.linkProjectToClient(client);
    }

    @Override
    public void unlinkClientFromProject(Project project) {
        requireNonNull(project);
        addressBook.unlinkClientFromProject(project);
    }

    @Override
    public void markProjectAsDone(Project project) {
        requireNonNull(project);
        addressBook.markProjectAsDone(project);
    }

    @Override
    public void markProjectAsNotDone(Project project) {
        requireNonNull(project);
        addressBook.markProjectAsNotDone(project);
    }

    @Override
    public int noProjectSize() {
        return addressBook.noProjectSize();
    }

    @Override
    public int noOverdueProjects() {
        return addressBook.noOverdueProjects();
    }

    @Override
    public int noDoneProjects() {
        return addressBook.noDoneProjects();
    }

    @Override
    public int noNotDoneProjects() {
        return addressBook.noNotDoneProjects();
    }

    @Override
    public void resetFilteredAndSortedClientList() {
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        updateSortedClientList(CLIENT_NO_COMPARATOR);
    }

    @Override
    public void resetFilteredAndSortedProjectList() {
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        updateSortedProjectList(PROJECT_NO_COMPARATOR);
    }

    //=========== Filtered Client List Accessors ============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    //=========== Filtered Project List Accessors ===========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    //=========== Sorted Client List Accessors ==============================================================

    @Override
    public SortedList<Client> getSortedClientList() {
        return sortedClients;
    }

    @Override
    public void updateSortedClientList(Comparator<Client> comparator) {
        sortedClients.setComparator(comparator);
    }

    //=========== Sorted Project List Accessors =============================================================

    @Override
    public SortedList<Project> getSortedProjectList() {
        return sortedProjects;
    }

    @Override
    public void updateSortedProjectList(Comparator<Project> comparator) {
        sortedProjects.setComparator(comparator);
    }

    //=========== Tag Mapping List Accessors ================================================================

    @Override
    public ObservableList<TagMapping> getTagMappingList() {
        return tagMappings;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        ModelManager other = (ModelManager) obj;

        // state check
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients)
                && filteredProjects.equals(other.filteredProjects)
                && sortedClients.equals(other.sortedClients)
                && sortedProjects.equals(other.sortedProjects)
                && new HashSet<>(tagMappings).equals(new HashSet<>(other.tagMappings));
    }

}
