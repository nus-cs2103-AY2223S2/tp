package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the friendly link data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FriendlyLink friendlyLink;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> filteredElderly;
    private final FilteredList<Person> filteredVolunteers;

    /**
     * Initializes a ModelManager with the given friendlyLink and userPrefs.
     */
    public ModelManager(ReadOnlyFriendlyLink friendlyLink, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(friendlyLink, userPrefs);

        logger.fine("Initializing with friendly link: " + friendlyLink + " and user prefs " + userPrefs);

        this.friendlyLink = new FriendlyLink(friendlyLink);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.friendlyLink.getPersonList());
        filteredElderly = new FilteredList<>(this.friendlyLink.getElderlyList());
        filteredVolunteers = new FilteredList<>(this.friendlyLink.getVolunteerList());
    }

    public ModelManager() {
        this(new FriendlyLink(), new UserPrefs());
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
    public Path getFriendlyLinkFilePath() {
        return userPrefs.getFriendlyLinkFilePath();
    }

    @Override
    public void setFriendlyLinkFilePath(Path friendlyLinkFilePath) {
        requireNonNull(friendlyLinkFilePath);
        userPrefs.setFriendlyLinkFilePath(friendlyLinkFilePath);
    }

    @Override
    public void setFriendlyLink(ReadOnlyFriendlyLink friendlyLink) {
        this.friendlyLink.resetData(friendlyLink);
    }

    @Override
    public ReadOnlyFriendlyLink getFriendlyLink() {
        return friendlyLink;
    }

    //=========== FriendlyLink Persons  ======================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return friendlyLink.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        friendlyLink.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        friendlyLink.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        friendlyLink.setPerson(target, editedPerson);
    }

    //=========== FriendlyLink Elderly  ======================================================================

    @Override
    public boolean hasElderly(Person e) {
        requireNonNull(e);
        return friendlyLink.hasElderly(e);
    }

    @Override
    public void deleteElderly(Person target) {
        friendlyLink.removeElderly(target);
    }

    @Override
    public void addElderly(Person person) {
        friendlyLink.addElderly(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setElderly(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        friendlyLink.setElderly(target, editedPerson);
    }


    //=========== FriendlyLink Volunteers  ======================================================================
    @Override
    public boolean hasVolunteer(Person person) {
        requireNonNull(person);
        return friendlyLink.hasVolunteer(person);
    }

    @Override
    public void deleteVolunteer(Person target) {
        friendlyLink.removeVolunteer(target);
    }

    @Override
    public void addVolunteer(Person person) {
        friendlyLink.addVolunteer(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setVolunteer(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        friendlyLink.setVolunteer(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedFriendlyLink}
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

    //=========== Filtered Elderly List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Elderly} backed by the internal list of
     * {@code versionedFriendlyLink}
     */
    @Override
    public ObservableList<Person> getFilteredElderlyList() {
        return filteredElderly;
    }

    @Override
    public void updateFilteredElderlyList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Volunteer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedFriendlyLink}
     */
    @Override
    public ObservableList<Person> getFilteredVolunteerList() {
        return filteredVolunteers;
    }

    @Override
    public void updateFilteredVolunteerList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return friendlyLink.equals(other.friendlyLink)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
