package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DukeDriverParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.timetable.TimetableParser;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.jobs.Earning;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

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
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = addressBookParser.parseCommand(commandText);
        return execute(command);
    }

    @Override
    public CommandResult execute(Command command) throws CommandException, ParseException {
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
    public CommandResult executeTimetableCommand(String commandText) throws CommandException, ParseException {
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
        double earnings = 0;
        for (DeliveryJob job: list) {
            Optional<Earning> earning = job.getEarning();
            Earning earn = earning.get();
            if (earn != null) {
                earnings += Double.parseDouble(earn.value);
            }
        }
        return earnings;
    }

    @Override
    public int getTotalCompleted(ObservableList<DeliveryJob> list) {
        int completed = 0;
        for (DeliveryJob job: list) {
            if (job.getDeliveredStatus()) {
                completed += 1;
            }
        }
        return completed;
    }

    @Override
    public int getTotalPending(ObservableList<DeliveryJob> list) {
        int pending = 0;
        for (DeliveryJob job: list) {
            if (!job.getDeliveredStatus()) {
                pending += 1;
            }
        }
        return pending;
    }

    public ObservableList<DeliveryJob> getUnscheduledDeliveryJobList() {
        return model.getUnscheduledDeliveryJobList();
    }

    @Override
    public ObservableList<Reminder> getReminderList() {
        return model.getReminderList();
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
