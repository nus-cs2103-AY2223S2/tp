package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.files.FilesManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.TimeComparator;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private FilteredList<Person> filteredPersonsByName;
    private ObservableList<Person> persons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredPersonsByName = new FilteredList<>(this.addressBook.getPersonListByName());
        persons = FXCollections.observableArrayList(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(persons);
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasClash(Person person, Index index) {
        requireNonNull(person);
        // check if person's appointment has clashes with appointments except for person with index
        // should check with Json file or lowest-level personlist
        return addressBook.hasClash(person, index);
    }

    @Override
    public void deletePerson(Person target) {
        FilesManager filesManager = new FilesManager(target);
        filesManager.deleteAll();
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        FilesManager filesManager = new FilesManager(person);
        filesManager.initFile();
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
        persons.setAll(addressBook.getPersonList());
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateScheduledList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        persons.setAll(addressBook.getPersonList());
        filteredPersons.setPredicate(predicate);
        SortedList<Person> newSortedList = new SortedList<>(filteredPersons, new TimeComparator());
        persons.setAll(newSortedList);
    }
    @Override
    public void updateFilteredPersonListByName(Predicate<Person> predicate) {
        requireNonNull(predicate);
        addressBook.setPersons(this.addressBook.getPersonListByName());
        updateFilteredPersonList(predicate);
    }

    @Override
    public void updateSearchAppointmentDate(Predicate<Person> predicate) {
        requireNonNull(predicate);
        persons.setAll(addressBook.getPersonList());
        filteredPersons.setPredicate(PREDICATE_SCHEDULED.and(predicate));
        SortedList<Person> newSortedList = new SortedList<>(filteredPersons, new TimeComparator());
        persons.setAll(newSortedList);
    }

    @Override
    public void updateFindAppointment(Predicate<Person> predicate) {
        requireNonNull(predicate);
        persons.setAll(addressBook.getPersonList());
        filteredPersons.setPredicate(predicate);
        SortedList<Person> newSortedList = new SortedList<>(filteredPersons, new TimeComparator());
        persons.setAll(newSortedList);
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
