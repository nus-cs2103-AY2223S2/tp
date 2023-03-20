package vimification.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import vimification.commons.core.GuiSettings;
import vimification.commons.core.LogsCenter;
import vimification.logic.commands.CreateCommand;
import vimification.logic.commands.LogicCommand;
import vimification.logic.commands.Command;
import vimification.logic.commands.CommandException;
import vimification.logic.commands.CommandResult;
import vimification.logic.parser.ParseException;
import vimification.model.LogicTaskList;
import vimification.model.Model;
import vimification.model.ReadOnlyTaskPlanner;
import vimification.model.task.Task;
import vimification.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final LogicTaskList taskList;
    private final Storage storage;
    private final ObservableList<Task> viewTaskList;
    // TODO : FIX THIS
    // private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(LogicTaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.viewTaskList = FXCollections.observableList(taskList.getInternalList());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("[USER COMMAND] " + commandText);

        // TODO : FIX THIS
        // Command command = addressBookParser.parseCommand(commandText);
        LogicCommand command = new CreateCommand(null);
        CommandResult result = command.execute(taskList);

        // TODO: Fix this later
        // Only save when the result indicates that the task list should be saved
        // try {
        // storage.saveTaskList(model.getTaskList());
        // } catch (IOException ioe) {
        // throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        // }

        return result;
    }

    // @Override
    // public ReadOnlyTaskPlanner getTaskList() {
    // return model.getTaskList();
    // }

    public ObservableList<Task> getFilteredTaskList() {
        return viewTaskList;
    }

    // @Override
    // public Path getTaskListFilePath() {
    // return model.getTaskListFilePath();
    // }

    // @Override
    // public GuiSettings getGuiSettings() {
    // return model.getGuiSettings();
    // }

    // @Override
    // public void setGuiSettings(GuiSettings guiSettings) {
    // model.setGuiSettings(guiSettings);
    // }
}
