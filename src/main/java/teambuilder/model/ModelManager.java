package teambuilder.model;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import teambuilder.commons.core.GuiSettings;
import teambuilder.commons.core.LogsCenter;
import teambuilder.commons.core.Memento;
import teambuilder.model.person.Person;
import teambuilder.model.team.Team;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeamBuilder teamBuilder;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;
    private final FilteredList<Team> filteredTeams;
    private final SortedList<Team> sortedTeams;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTeamBuilder addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.teamBuilder = new TeamBuilder(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.teamBuilder.getPersonList());
        sortedPersons = new SortedList<>(filteredPersons);
        filteredTeams = new FilteredList<>(this.teamBuilder.getTeamList());
        sortedTeams = new SortedList<>(filteredTeams);
    }


    public ModelManager() {
        this(new TeamBuilder(), new UserPrefs());
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
    public Memento save() {
        return new TeamBuilderMemento(new TeamBuilder(teamBuilder), this);
    }

    @Override
    public void setTeamBuilder(ReadOnlyTeamBuilder teamBuilder) {
        this.teamBuilder.resetData(teamBuilder);
    }

    @Override
    public ReadOnlyTeamBuilder getTeamBuilder() {
        return teamBuilder;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return teamBuilder.hasPerson(person);
    }

    @Override
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return teamBuilder.hasTeam(team);
    }

    @Override
    public void deletePerson(Person target) {
        teamBuilder.removePerson(target);
    }

    @Override
    public void deleteTeam(Team target) {
        teamBuilder.removeTeam(target);
    }

    @Override
    public void addPerson(Person person) {
        teamBuilder.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTeam(Team team) {
        teamBuilder.addTeam(team);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        teamBuilder.setPerson(target, editedPerson);
    }

    @Override
    public void setTeam(Team target, Team editedTeam) {
        requireAllNonNull(target, editedTeam);

        teamBuilder.setTeam(target, editedTeam);
    }
    //=========== Filtered Team List Accessors ===============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Team} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Team> getTeamList() {
        return filteredTeams;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public ObservableList<Team> getSortedTeamList() {
        return sortedTeams;
    }

    @Override
    public void removeFromAllTeams(Person person) {
        teamBuilder.removeFromAllTeams(person);
    }


    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTeamList(Predicate<Team> predicate) {
        requireNonNull(predicate);
        filteredTeams.setPredicate(predicate);
    }

    @Override
    public void updateSortPerson(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
    }

    @Override
    public void updateSortTeam(Comparator<Team> comparator) {
        requireNonNull(comparator);
        sortedTeams.setComparator(comparator);
    }

    @Override
    public void updatePersonInTeams(Person person) {
        teamBuilder.updatePersonInTeams(person);
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

        // state check
        ModelManager other = (ModelManager) obj;
        return teamBuilder.equals(other.teamBuilder)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTeams.equals(other.filteredTeams);
    }

}
