package arb.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import arb.commons.core.GuiSettings;
import arb.model.client.Client;
import arb.model.project.Project;
import arb.model.tag.TagMapping;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} for filtered client list that always evaluates to true */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /** {@code Predicate} for filtered project list that always evaluates to true */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /** {@code comparator} for sorted client list that compares using client names */
    Comparator<Client> CLIENT_NAME_COMPARATOR = (c1, c2) -> {
        return c1.getName().compareTo(c2.getName());
    };

    /** {@code comparator} for sorted project list that compares using project titles */
    Comparator<Project> PROJECT_TITLE_COMPARATOR = (p1, p2) -> {
        return p1.getTitle().compareTo(p2.getTitle());
    };

    /** {@code comparator} for sorted project list that compares using project deadlines */
    Comparator<Project> PROJECT_DEADLINE_COMPARATOR = (p1, p2) -> {
        if (!p1.isDeadlinePresent() && !p2.isDeadlinePresent()) {
            return 0;
        } else if (!p1.isDeadlinePresent()) {
            return 1;
        } else if (!p2.isDeadlinePresent()) {
            return -1;
        }
        return p1.getDeadline().compareTo(p2.getDeadline());
    };

    /** {@code comparator} for sorted project list that compares using project prices */
    Comparator<Project> PROJECT_PRICE_COMPARATOR = (p1, p2) -> {
        if (!p1.isPricePresent() && !p2.isPricePresent()) {
            return 0;
        } else if (!p1.isPricePresent()) {
            return 1;
        } else if (!p2.isPricePresent()) {
            return -1;
        }
        return p1.getPrice().compareTo(p2.getPrice());
    };

    /** Null {@code comparator} for sorted client list */
    Comparator<Client> CLIENT_NO_COMPARATOR = null;

    /** Null {@code comparator} for sorted project list */
    Comparator<Project> PROJECT_NO_COMPARATOR = null;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Empties the project list of the address book. */
    void resetProjectList();

    /** Empties the client list of the address book. */
    void resetClientList();

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    boolean hasClient(Client client);

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given client.
     * The client must exist in the address book.
     */
    void deleteClient(Client target);

    /**
     * Deletes the given project.
     * The project must exist in the address book.
     */
    void deleteProject(Project target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the address book.
     */
    void addClient(Client client);

    /**
     * Adds the given project.
     * {@code project} must not already exist in the address book.
     */
    void addProject(Project project);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    void setClient(Client target, Client editedClient);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the address book.
     * The project identity of {@code editedProject} must not be the same as another existing project
     * in the address book.
     */
    void setProject(Project target, Project editedProject);

    /**
     * Returns the number of clients in the addressbook that fulfill {@code predicate}.
     */
    int numberOfClientsMatchingPredicate(Predicate<Client> predicate);

    /**
     * Sets the {@code project} to be linked to a client.
     */
    void setProjectToLink(Project project);

    /**
     * Resets the {@code project} to be linked to a client.
     */
    void resetProjectToLink();

    /**
     * Links a project to {@code client}.
     */
    void linkProjectToClient(Client client);

    /**
     * Unlinks the client from {@code project}.
     */
    void unlinkClientFromProject(Project project);

    /**
     * Marks {@code project} as done.
     */
    public void markProjectAsDone(Project project);

    /**
     * Marks {@code project} as not done.
     */
    public void markProjectAsNotDone(Project project);

    /** Returns an unmodifiable view of the filtered client list */
    ObservableList<Client> getFilteredClientList();

    /** Returns an unmodifiable view of the filtered project list */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    /**
     * Updates the filter of the filtered project list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);

    /** Returns an unmodifiable view of the sorted client list */
    ObservableList<Client> getSortedClientList();

    /** Returns an unmodifiable view of the sorted project list */
    ObservableList<Project> getSortedProjectList();

    /**
     * Updates the comparator of the sorted client list to filter by the given {@code comparator}.
     */
    void updateSortedClientList(Comparator<Client> comparator);

    /**
     * Updates the comparator of the sorted project list to filter by the given {@code comparator}.
     */
    void updateSortedProjectList(Comparator<Project> comparator);

    /** Returns an unmodifiable view of the tag mapping list */
    ObservableList<TagMapping> getTagMappingList();

    /**
     * Returns number of Projects in Project list.
     */
    int noProjectSize();

    /**
     * Returns number of Overdue Projects.
     */
    int noOverdueProjects();

    /**
     * Returns number of Done Projects.
     * @return
     */
    int noDoneProjects();

    /**
     * Returns number of Not Done Projects.
     * @return
     */
    int noNotDoneProjects();

    /**
     * Resets the filtered and sorted client lists to their
     * initial states.
     */
    void resetFilteredAndSortedClientList();

    /**
     * Resets the filtered and sorted project lists to their
     * initial states.
     */
    void resetFilteredAndSortedProjectList();
}
