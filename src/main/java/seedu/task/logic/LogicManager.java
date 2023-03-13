package seedu.task.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.task.commons.core.GuiSettings;
import seedu.task.commons.core.LogsCenter;
import seedu.task.logic.commands.Command;
import seedu.task.logic.commands.CommandResult;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.logic.parser.TaskBookParser;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.Model;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.task.StartupTaskWithinTimelinePredicate;
import seedu.task.model.task.Task;
import seedu.task.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TaskBookParser taskBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        taskBookParser = new TaskBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = taskBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTaskBook(model.getTaskBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return model.getTaskBook();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getAlertTaskList() {
        model.updateAlertTaskList(new StartupTaskWithinTimelinePredicate());
        return model.getAlertTaskList();
    }

    @Override
    public Path getTaskBookFilePath() {
        return model.getTaskBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
