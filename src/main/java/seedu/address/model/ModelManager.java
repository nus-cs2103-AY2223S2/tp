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
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FriendlyLink friendlyLink;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Pair> filteredPairs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyFriendlyLink addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.friendlyLink = new FriendlyLink(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.friendlyLink.getPersonList());
        filteredPairs = new FilteredList<>(this.friendlyLink.getPairList());
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
    public void setFriendlyLinkFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setFriendlyLinkFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setFriendlyLink(ReadOnlyFriendlyLink addressBook) {
        this.friendlyLink.resetData(addressBook);
    }

    @Override
    public ReadOnlyFriendlyLink getFriendlyLink() {
        return friendlyLink;
    }

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

    @Override
    public boolean hasPair(Pair pair) {
        requireNonNull(pair);
        return friendlyLink.hasPair(pair);
    }

    @Override
    public void deletePair(Pair target) {
        // TODO: implement addressBook.removePair(target);
    }

    @Override
    public void addPair(Pair pair) {
        friendlyLink.addPair(pair);
        // TODO: implement updateFilteredPersonList(PREDICATE_SHOW_ALL_PAIRS);
    }

    @Override
    public void setPair(Pair target, Pair editedPair) {
        requireAllNonNull(target, editedPair);
        // TODO: implement addressBook.setPair(target, editedPair);
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

    //=========== Filtered Pair List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Pair} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Pair> getFilteredPairList() {
        return filteredPairs;
    }

    @Override
    public void updateFilteredPairList(Predicate<Pair> predicate) {
        requireNonNull(predicate);
        // TODO: implement filteredPairs.setPredicate(predicate);
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
