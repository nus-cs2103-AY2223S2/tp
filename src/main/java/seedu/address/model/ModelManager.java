package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.crew.Crew;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final IdentifiableManager<Crew> crewManager;
    private final FilteredList<Crew> filteredCrew;
    private final ObservableList<Crew> crewList;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyIdentifiableManager crewManager) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.crewManager = new IdentifiableManager(crewManager);
        filteredCrew = new FilteredList<Crew>(this.crewManager.getItemList());
        crewList = FXCollections.observableArrayList();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new IdentifiableManager<>());
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

    //=========== CrewManager ==================================================================================

    @Override
    public void setCrewManager(ReadOnlyIdentifiableManager Crew) {
        requireNonNull(Crew);
        this.crewManager.resetData(Crew);
    }

    @Override
    public ReadOnlyIdentifiableManager<Crew> getCrewManager() {
        return crewManager;
    }

    @Override
    public Path getCrewManagerFilePath() {
        return userPrefs.getCrewManagerFilePath();
    }

    @Override
    public void setCrewManagerFilePath(Path crewManagerFilePath) {
        requireNonNull(crewManagerFilePath);
        userPrefs.setCrewManagerFilePath(crewManagerFilePath);
    }

    @Override
    public boolean hasCrew(Crew crew) {
        requireNonNull(crew);
        return this.crewManager.hasItem(crew);
    }

    @Override
    public boolean hasCrew(String id) {
        requireNonNull(id);
        return this.crewManager.hasItem(id);
    }

    @Override
    public void addCrew(Crew crew) {
        crewManager.addItem(crew);
        updateFilteredCrewList(PREDICATE_SHOW_ALL_CREW);
    }

    @Override
    public void deleteCrew(Crew crew) {
        crewManager.removeItem(crew);
    }

    @Override
    public void deleteCrew(String id) {
        crewManager.removeItem(id);
    }

    @Override
    public void setCrew(Crew target, Crew editedCrew) {
        requireAllNonNull(target, editedCrew);
        crewManager.setItem(target, editedCrew);
    }

    @Override
    public ObservableList<Crew> getFilteredCrewList() {
        return filteredCrew;
    }

    @Override
    public void updateFilteredCrewList(Predicate<Crew> predicate) {
        requireNonNull(predicate);
        filteredCrew.setPredicate(predicate);
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
                && filteredPersons.equals(other.filteredPersons)
                && crewManager.equals(other.crewManager);
    }

}
