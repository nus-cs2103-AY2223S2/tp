package teambuilder.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import teambuilder.commons.core.GuiSettings;
import teambuilder.commons.core.Originator;
import teambuilder.model.person.Person;
import teambuilder.model.team.Team;

/**
 * The API of the Model component.
 */
public interface Model extends Originator {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Team> PREDICATE_SHOW_ALL_TEAMS = unused -> true;

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
    void setTeamBuilder(ReadOnlyTeamBuilder teamBuilder);

    /** Returns the AddressBook */
    ReadOnlyTeamBuilder getTeamBuilder();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);
    /**
     * Returns true if a team with the same identity as {@code team} exists in the address book.
     */
    boolean hasTeam(Team team);

    void setTeam(Team team, Team editedTeam);
    /**
     * Deletes the given team.
     * The team must exist in the address book.
     */
    void deleteTeam(Team target);

    /**
     * Adds the given team.
     * {@code team} must not already exist in the address book.
     */
    void addTeam(Team team);

    /**
     * Adds person to the team, based on its team tags.
     */
    void updatePersonInTeams(Person person);

    /**
     * Removes person from all teams
     */
    void removeFromAllTeams(Person person);

    ObservableList<Team> getSortedTeamList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getSortedPersonList();

    /** Returns an unmodifiable view of the team list */
    ObservableList<Team> getTeamList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredTeamList(Predicate<Team> predicate);

    /**
     * Updates the comparator of the sorted person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortPerson(Comparator<Person> comparator);

    void updateSortTeam(Comparator<Team> comparator);

}
