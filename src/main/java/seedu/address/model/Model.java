package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.stats.WeeklyStats;

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

    /**
     * Returns person with specified ID
     * @param id
     */
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

    /**
     * Returns the user prefs' delivery job system file path.
     */
    Path getDeliveryJobSystemFilePath();

    /**
     * Sets the user prefs' delivery job system file path.
     */
    void setDeliveryJobSystemFilePath(Path deliveryJobSystemFilePath);

    /**
     * Sets delivery job system
     * @param jobSystem
     */
    void setDeliveryJobSystem(ReadOnlyDeliveryJobSystem jobSystem);

    /**
     * Returns delivery job system
     */
    ReadOnlyDeliveryJobSystem getDeliveryJobSystem();

    /**
     * Returns the filtered delivery job list
     */
    ObservableList<DeliveryJob> getFilteredDeliveryJobList();

    /**
     * Checks if the job list has a certain job
     * @param job job to find
     */
    boolean hasDeliveryJob(DeliveryJob job);

    /**
     * Deletes delivery job in job list
     * @param target job to delete
     */
    void deleteDeliveryJob(DeliveryJob target);

    /**
     * Adds delivery job to job list
     * @param job job to add
     */
    void addDeliveryJob(DeliveryJob job);

    /**
     * Sets/updates delivery job in job list
     * @param target job to edit/to be replaced
     * @param editedJob new job to replace
     */
    void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob);

    /**
     * Returns job list
     */
    ObservableList<DeliveryJob> getDeliveryJobList();

    /**
     * Returns job list sorted
     */
    ObservableList<DeliveryJob> getSortedDeliveryJobListByComparator();

    /**
     * Updates filtered delivery job list based on new predicate
     * @param predicate
     */
    void updateFilteredDeliveryJobList(Predicate<DeliveryJob> predicate);

    /**
     * Updates sorted delivery job list based on new sorter
     * @param sorter
     */
    void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter);

    /**
     * Updates sorted delivery job list based on new sorter
     * @param sorter
     */
    void updateSortedDeliveryJobListByComparator(Comparator<DeliveryJob> sorter);

    /**
     * Updates sorted delivery job list by date and earning
     */
    void updateSortedDeliveryJobListByDate();

    /**
     * Updates delivery job list in week containing given date
     * @param date date to focus
     */
    void updateWeekDeliveryJobList(LocalDate date);

    /**
     * Updates focus date
     * @param jobDate
     */
    void updateFocusDate(LocalDate jobDate);

    /**
     * Returns sorted delivery job list
     */
    ObservableList<DeliveryJob> getSortedDeliveryJobList();

    /**
     * Returns sorted delivery job list by date
     */
    Map<LocalDate, DeliveryList> getSortedDeliveryJobListByDate();

    /**
     * Returns job list in the week
     */
    Map<LocalDate, DeliveryList> getWeekDeliveryJobList();

    /**
     * Returns job list in a specific day of week
     * @param dayOfWeek
     */
    DeliveryList getDayOfWeekJob(int dayOfWeek);

    /**
     * Returns list of unscheduled jobs
     */
    ObservableList<DeliveryJob> getUnscheduledDeliveryJobList();

    /**
     * Returns list of completed jobs
     */
    ObservableList<DeliveryJob> getCompletedDeliveryJobList();

    /**
     * Returns focus date
     */
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
     * Returns an unmodifiable view of the filtered reminder list
     */
    ObservableList<Reminder> getReminderList();

    /**
     * Sorts reminder list
     */

    void sortReminderList();

    /**
     * Indicate that a reminder has already been shown for this app's runtime
     */
    void setHasShown(int i, boolean b);

    // STATISTICS =========================================

    /**
     * Checks if the given job should be place in weekly stats.
     */
    boolean sameWeek(DeliveryJob job, WeeklyStats weeklyStats);

    /**
     * Returns an unmodifiable view of the Delivery Job list with only
     * jobs that are in the same week as the given date.
     */
    ObservableList<DeliveryJob> weekJobsList(ObservableList<DeliveryJob> list, LocalDate date);

    /**
     * Returns the total earnings of delivery jobs in the list.
     */
    double getTotalEarnings(ObservableList<DeliveryJob> list);

    /**
     * Returns the total number of completed delivery jobs in the list.
     */
    int getTotalCompleted(ObservableList<DeliveryJob> list);

    /**
     * Returns the total number of pending delivery jobs in the list.
     */
    int getTotalPending(ObservableList<DeliveryJob> list);
}
