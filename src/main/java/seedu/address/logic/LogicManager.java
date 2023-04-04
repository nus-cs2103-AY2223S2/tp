package seedu.address.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DukeDriverParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.timetable.TimetableParser;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.jobs.sorters.SortbyTimeAndEarn;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.stats.WeeklyStats;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    public static final SortbyTimeAndEarn SORTER_BY_DATE = new SortbyTimeAndEarn();


    private final Model model;
    private final Storage storage;
    private final DukeDriverParser addressBookParser;
    private final TimetableParser timetableParser;


    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new DukeDriverParser();
        timetableParser = new TimetableParser();
    }

    public Model getModel() {
        return this.model;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, FileNotFoundException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = addressBookParser.parseCommand(commandText);
        return execute(command);
    }

    @Override
    public CommandResult execute(String commandText, Predicate<CommandGroup> condition)
            throws CommandException, ParseException, FileNotFoundException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        if (condition.test(addressBookParser.parseCommandGroup(commandText))) {
            return execute(addressBookParser.parseCommand(commandText));
        } else {
            return new CommandResult(String.format(Messages.COMMAND_NOT_ALLOW));
        }
    }

    @Override
    public CommandResult execute(Command command) throws CommandException, ParseException, FileNotFoundException {
        logger.info("----------------[USER COMMAND][" + command.getClass().getSimpleName() + "]");
        CommandResult commandResult = command.execute(model);
        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveDeliveryJobSystem(model.getDeliveryJobSystem());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }

    @Override
    public CommandResult executeTimetableCommand(String commandText)
            throws CommandException, ParseException, FileNotFoundException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = timetableParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveDeliveryJobSystem(model.getDeliveryJobSystem());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;

    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<DeliveryJob> getFilteredDeliveryJobList() {
        return model.getDeliveryJobList();
    }

    @Override
    public ObservableList<DeliveryJob> getSortedDeliveryJobList() {
        return model.getSortedDeliveryJobListByComparator();
    }

    @Override
    public Map<LocalDate, DeliveryList> getWeekDeliveryJobList() {
        return model.getWeekDeliveryJobList();
    }

    @Override
    public DeliveryList getDayofWeekJob(int dayOfWeek) {
        return model.getDayOfWeekJob(dayOfWeek);
    }

    @Override
    public double getTotalEarnings(ObservableList<DeliveryJob> list) {
        return model.getTotalEarnings(list);
    }

    @Override
    public int getTotalCompleted(ObservableList<DeliveryJob> list) {
        return model.getTotalCompleted(list);
    }

    @Override
    public int getTotalPending(ObservableList<DeliveryJob> list) {
        return model.getTotalPending(list);
    }

    @Override
    public boolean sameWeek(DeliveryJob job, WeeklyStats weeklyStats) {
        return model.sameWeek(job, weeklyStats);
    }

    @Override
    public ObservableList<DeliveryJob> weekJobsList(ObservableList<DeliveryJob> list, LocalDate date) {
        return model.weekJobsList(list, date);
    }

    public ObservableList<DeliveryJob> getUnscheduledDeliveryJobList() {
        return model.getUnscheduledDeliveryJobList();
    }

    @Override
    public ObservableList<Reminder> getReminderList() {
        return model.getReminderList();
    }

    @Override
    public void sortReminderList() {
        model.sortReminderList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public Path getDeliveryJobSystemFilePath() {
        return model.getDeliveryJobSystemFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void setWeekDeliveryJobList(LocalDate focusDate) {
        model.updateFocusDate(focusDate);
        model.updateWeekDeliveryJobList(focusDate);
    }

    @Override
    public void updateFilteredDeliveryJobList(Predicate<DeliveryJob> pre) {
        model.updateFilteredDeliveryJobList(pre);
    }

    @Override
    public void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter) {
        model.updateSortedDeliveryJobList(sorter);
    }

    public void updateSortedDeliveryJobListByComparator(Comparator<DeliveryJob> sorter) {
        model.updateSortedDeliveryJobListByComparator(sorter);
    }

    @Override
    public void updateSortedDeliveryJobListByDate() {
        model.updateSortedDeliveryJobList(SORTER_BY_DATE);
        model.updateSortedDeliveryJobListByDate();
    }

    @Override
    public ObservableList<DeliveryJob> getCompletedDeliveryJobList() {
        return model.getCompletedDeliveryJobList();
    }

    @Override
    public LocalDate getFocusDate() {
        return model.getFocusDate();
    }


}
