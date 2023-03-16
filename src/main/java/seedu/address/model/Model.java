package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<DeliveryJob> PREDICATE_SHOW_ALL_DELIVERY_JOBS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

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
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    Optional<Person> getPersonById(String id);

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
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // DELIVERY JOB SYSTEM ===================================

    void setDeliveryJobSystem(ReadOnlyDeliveryJobSystem jobSystem);

    ReadOnlyDeliveryJobSystem getDeliveryJobSystem();

    boolean hasDeliveryJob(DeliveryJob job);

    void deleteDeliveryJob(DeliveryJob target);

    void addDeliveryJob(DeliveryJob job);

    void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob);

    ObservableList<DeliveryJob> getDeliveryJobList();

    void updateFilteredDeliveryJobList(Predicate<DeliveryJob> predicate);
    void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter);
    void updateSortedDeliveryJobListByDate();
    void updateWeekDeliveryJobList(LocalDate date);
    void updateFocusDate(LocalDate jobDate);
    ObservableList<DeliveryJob> getSortedDeliveryJobList();
    Map<LocalDate, ArrayList<ArrayList<DeliveryJob>>> getSortedDeliveryJobListByDate();
    Map<LocalDate, ArrayList<ArrayList<DeliveryJob>>> getWeekDeliveryJobList();
    ArrayList<ArrayList<DeliveryJob>> getDayOfWeekJob(int dayOfWeek);
    LocalDate getFocusDate();



    // NOTIFICATION =========================================

    /**
     * Deletes the given reminder.
     * The reminder must exist in reminders.
     */
    void deleteReminder(int i);

    /**
     * Adds the given reminder.
     */
    void addReminder(Reminder reminder);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Reminder> getReminderList();
}
