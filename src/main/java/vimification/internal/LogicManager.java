package vimification.internal;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import vimification.commons.core.LogsCenter;
import vimification.internal.command.Command;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.internal.parser.ParserException;
import vimification.internal.parser.VimificationParser;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.MacroMap;
import vimification.model.task.Task;
import vimification.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    private static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private static final Logger LOGGER = LogsCenter.getLogger(LogicManager.class);


    private final LogicTaskList logicTaskList;
    private final MacroMap macroMap;
    private final CommandStack commandStack;
    private final Storage storage;

    private final VimificationParser vimificationParser;
    private final ObservableList<Task> viewTaskList;


    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(
            LogicTaskList logicTaskList,
            MacroMap macroMap,
            CommandStack commandStack,
            Storage storage) {
        this.logicTaskList = logicTaskList;
        this.macroMap = macroMap;
        this.commandStack = commandStack;
        this.storage = storage;

        this.viewTaskList = FXCollections.observableList(logicTaskList.getInternalList());
        this.vimificationParser = VimificationParser.getInstance(macroMap);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParserException {
        LOGGER.info("[USER COMMAND] " + commandText);

        Command command = vimificationParser.parse(commandText);
        CommandResult result = command.execute(logicTaskList);
        updateViewTaskList(command);

        // if command is related to logic task list and command stack, pass these 2
        // if command is related to macro, pass the macro map
        // if command is related to the view, pass the view task list

        // TODO: Fix this later
        // Only save when the result indicates that the task list should be saved
        try {
            storage.saveLogicTaskList(logicTaskList);
        } catch (IOException ex) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ex, ex);
        }
        return result;
    }

    private void updateViewTaskList(Command command) {
        ObservableList<Task> newViewTaskList = command.getViewTaskList();
        if (newViewTaskList != null) {
            viewTaskList.setAll(newViewTaskList);
        }
    }

    // @Override
    // public ReadOnlyTaskPlanner getTaskList() {
    // return model.getTaskList();
    // }

    public ObservableList<Task> getViewTaskList() {
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
