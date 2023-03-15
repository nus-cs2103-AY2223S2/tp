package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final DeliveryJobSystem deliveryJobSystem;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<DeliveryJob> filteredDeliveryJobs;
    private final List<DeliveryJob> sortedDeliveryJobs;

    private final ObservableList<Reminder> reminderList;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     * @param addressBook
     * @param deliveryJobSystem
     * @param userPrefs
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyDeliveryJobSystem deliveryJobSystem,
            ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, deliveryJobSystem, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.deliveryJobSystem = new DeliveryJobSystem(deliveryJobSystem);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredDeliveryJobs = new FilteredList<>(this.deliveryJobSystem.getDeliveryJobList());
        this.sortedDeliveryJobs = new ArrayList<DeliveryJob>(this.deliveryJobSystem.getDeliveryJobList());
        this.reminderList = this.addressBook.getReminderList();
    }

    /**
     * ModelManager.
     */
    public ModelManager() {
        this(new AddressBook(), new DeliveryJobSystem(), new UserPrefs());
    }

    // UserPrefs ===================================================================

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

    // AddressBook ===============================================================

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
    public Optional<Person> getPersonById(String id){
        requireNonNull(id);
        return addressBook.getPersonById(id);
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

    // =========== Filtered Person List Accessors ==================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
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

    // DeliveryJob System =====================================================

    @Override
    public void setDeliveryJobSystem(ReadOnlyDeliveryJobSystem jobSystem) {
        this.deliveryJobSystem.resetData(jobSystem);
    }

    @Override
    public ReadOnlyDeliveryJobSystem getDeliveryJobSystem() {
        return deliveryJobSystem;
    }

    @Override
    public boolean hasDeliveryJob(DeliveryJob job) {
        requireNonNull(job);
        return deliveryJobSystem.hasDeliveryJob(job);
    }

    @Override
    public void deleteDeliveryJob(DeliveryJob target) {
        deliveryJobSystem.removeDeliveryJob(target);
    }

    @Override
    public void addDeliveryJob(DeliveryJob job) {
        deliveryJobSystem.addDeliveryJob(job);
        updateFilteredDeliveryJobList(PREDICATE_SHOW_ALL_DELIVERY_JOBS);
    }

    @Override
    public void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob) {
        requireAllNonNull(target, editedJob);

        deliveryJobSystem.setDeliveryJob(target, editedJob);
    }

    // =========== Filtered Delivery Job List Accessors ============

    @Override
    public ObservableList<DeliveryJob> getDeliveryJobList() {
        return filteredDeliveryJobs;
    }

    @Override
    public void updateFilteredDeliveryJobList(Predicate<DeliveryJob> predicate) {
        requireAllNonNull(predicate);
        filteredDeliveryJobs.setPredicate(predicate);
    }

    @Override
    public void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter) {
        Collections.sort(sortedDeliveryJobs, sorter);
    }

    @Override
    public ObservableList<DeliveryJob> getSortedDeliveryJobList() {
        return FXCollections.observableArrayList(sortedDeliveryJobs);
    }

    //=========== ReminderList Accessors =============================================================

    @Override
    public void deleteReminder(int i) {
        addressBook.removeReminder(i);
    }

    @Override
    public void addReminder(Reminder reminder) {
        addressBook.addReminder(reminder);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Reminder> getReminderList() {
        return reminderList;
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
