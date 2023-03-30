package seedu.address.logic;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.stats.WeeklyStats;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, FileNotFoundException;

    /**
     * Executes the command for chosen group and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @param condition execute when true.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText, Predicate<CommandGroup> condition)
            throws CommandException, ParseException, FileNotFoundException;

    /**
     * Executes specific command object and returns the result.
     *
     * @param command Prebuild command object.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(Command command) throws CommandException, ParseException, FileNotFoundException;

    CommandResult executeTimetableCommand(String commandText)
            throws CommandException, ParseException, FileNotFoundException;

    // ADDRESS BOOK SYSTEM ===================================

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    // REMINDER ===================================
    /**
     * Returns an unmodifiable view of the filtered list of reminders
     */
    ObservableList<Reminder> getReminderList();

    /**
     * Sorts reminder list
     */
    void sortReminderList();

    // DELIVERY JOB SYSTEM ===================================

    /**
     * Returns an unmodifiable view of the filtered list of delivery jobs
     */
    ObservableList<DeliveryJob> getFilteredDeliveryJobList();

    /**
     * Returns an unmodifiable view of the sorted list of delivery jobs
     */
    ObservableList<DeliveryJob> getSortedDeliveryJobList();

    /**
     * Returns delivery job list in the week sorted into day
     */
    Map<LocalDate, DeliveryList> getWeekDeliveryJobList();

    /**
     * Returns job on specific day of week
     * @param dayOfWeek day of week
     * @return job list in the specific day
     */
    DeliveryList getDayofWeekJob(int dayOfWeek);

    /**
     * Returns an unmodifiable view of the list of unscheduled delivery jobs,
     * sorted by time and earning
     */
    ObservableList<DeliveryJob> getUnscheduledDeliveryJobList();

    /**
     * Returns an unmodifiable view of the list of completed delivery jobs,
     * sorted by time and earning
     */
    ObservableList<DeliveryJob> getCompletedDeliveryJobList();

    /**
     * Gets total earning of all jobs in job list
     * @param list
     */
    double getTotalEarnings(ObservableList<DeliveryJob> list);

    /**
     * Gets total number of completed jobs in job list
     * @param list
     */
    int getTotalCompleted(ObservableList<DeliveryJob> list);

    int getTotalPending(ObservableList<DeliveryJob> list);

    ObservableList<DeliveryJob> weekJobsList(ObservableList<DeliveryJob> list, LocalDate date);

    boolean sameWeek(DeliveryJob job, WeeklyStats weeklyStats);

    /**
     * Returns the user prefs' delivery job system file path.
     */
    Path getDeliveryJobSystemFilePath();

    /**
     * Set focus date
     * @param focusDate
     */
    void setWeekDeliveryJobList(LocalDate focusDate);

    /**
     * Updates filter delivery job list
     * @return
     */
    void updateFilteredDeliveryJobList(Predicate<DeliveryJob> pre);

    /**
     * Updates sorted delivery job list
     * @return
     */
    void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter);

    /**
     * Updates sorted delivery job list
     * @return
     */
    void updateSortedDeliveryJobListByComparator(Comparator<DeliveryJob> sorter);

    /**
     * Updates sorted delivery job list by date
     */
    void updateSortedDeliveryJobListByDate();

    /**
     * Gets user input focus date
     * @return focus date
     */
    LocalDate getFocusDate();

    // MODEL ===================================

    /**
     * Gets model
     * @return model
     */
    Model getModel();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
