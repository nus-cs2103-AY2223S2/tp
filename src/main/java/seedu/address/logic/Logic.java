package seedu.address.logic;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

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
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Executes specific command object and returns the result.
     *
     * @param command Prebuild command object.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(Command command) throws CommandException, ParseException;

    CommandResult executeTimetableCommand(String commandText) throws CommandException, ParseException;

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
     * Returns an unmodifiable view of the filtered list of delivery jobs
     */
    ObservableList<DeliveryJob> getFilteredDeliveryJobList();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Reminder> getReminderList();

    /**
     * Returns a map of delivery job list in the week
     */
    Map<LocalDate, DeliveryList> getWeekDeliveryJobList();

    /**
     * Returns job on specific day of week
     * @param dayOfWeek day of week
     * @return job list in the specific day
     */
    DeliveryList getDayofWeekJob(int dayOfWeek);
    /**
     * Returns an unmodifiable view of the list of unscheduled delivery jobs
     */
    ObservableList<DeliveryJob> getUnscheduledDeliveryJobList();

    /**
     * Returns an unmodifiable view of the list of completed delivery jobs
     */
    ObservableList<DeliveryJob> getCompletedDeliveryJobList();

    double getTotalEarnings(ObservableList<DeliveryJob> list);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' delivery job system file path.
     */
    Path getDeliveryJobSystemFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Set focus date
     * @param focusDate
     */
    void setWeekDeliveryJobList(LocalDate focusDate);

    /**
     * Updates sorted delivery job list by date
     */
    void updateSortedDeliveryJobListByDate();

    /**
     * Gets user input focus date
     * @return focus date
     */
    LocalDate getFocusDate();

    /**
     * Get model
     * @return model
     */
    Model getModel();
}
