package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.history.History;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final History history;
    private final FilteredList<Person> filteredPersons;
    private ArrayList<Filter> applyingFilters;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, History history) {
        requireAllNonNull(addressBook, userPrefs, history);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.history = new History(history);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
        applyingFilters = new ArrayList<>();
    }

    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        this(addressBook, userPrefs, new History());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new History());
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

    @Override
    public ModelManager stateDetachedCopy() {
        ModelManager copy = new ModelManager(addressBook.deepCopy(), userPrefs);
        copy.updateFilteredPersonList(filteredPersons.getPredicate());
        return copy;
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

    @Override
    public void addTag(Person person, Tag tag) {
        addressBook.addTag(person, tag);
    }

    @Override
    public void deleteTag(Person person, Tag tag) {
        addressBook.deleteTag(person, tag);
    }

    //=========== History ================================================================================

    @Override
    public Path getHistoryStoragePath() {
        return history.getHistoryStoragePath();
    }

    @Override
    public void setHistoryStoragePath(Path newPath) {
        history.setHistoryStoragePath(newPath);
    }

    @Override
    public History getHistory() {
        return history;
    }

    @Override
    public void setHistory(History newHistory) {
        history.resetData(newHistory);
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

    /**
     * Returns an unmodifiable view of the list of applying {@code Filter}.
     */
    @Override
    public ObservableList<Filter> getApplyingFilterList() {
        return FXCollections.observableList(applyingFilters);
    }

    @Override
    public void updateFilteredPersonList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        updateFilteredPersonList(predicate, Stream.empty());
    }

    @Override
    public void updateFilteredPersonList(Predicate<? super Person> predicate, Stream<Filter> filtersFromPredicate) {
        requireNonNull(predicate);
        applyingFilters = filtersFromPredicate.collect(Collectors.toCollection(ArrayList::new));
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public Predicate<? super Person> getPredicate() {
        return filteredPersons.getPredicate();
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
