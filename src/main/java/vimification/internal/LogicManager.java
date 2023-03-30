package vimification.internal;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import vimification.commons.core.LogsCenter;
import vimification.internal.command.Command;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.internal.command.logic.LogicCommand;
import vimification.internal.command.macro.MacroCommand;
import vimification.internal.command.misc.MiscCommand;
import vimification.internal.command.view.ViewCommand;
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

    private static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file";
    private static final Logger LOGGER = LogsCenter.getLogger(LogicManager.class);


    private final LogicTaskList logicTaskList;
    private final MacroMap macroMap;
    private final CommandStack commandStack;
    private final Storage storage;

    private final VimificationParser vimificationParser;
    private final FilteredList<Task> viewTaskList;


    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(
            LogicTaskList logicTaskList,
            MacroMap macroMap,
            CommandStack commandStack,
            Storage storage) {

        List<Task> taskList = logicTaskList.getInternalList();
        ObservableList<Task> observableTaskList = FXCollections.observableList(taskList);
        logicTaskList.setInternalList(observableTaskList);
        this.logicTaskList = logicTaskList;
        this.macroMap = macroMap;
        this.commandStack = commandStack;
        this.storage = storage;

        this.viewTaskList = new FilteredList<>(observableTaskList);
        this.vimificationParser = VimificationParser.getInstance(macroMap);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParserException {
        LOGGER.info("[USER COMMAND] " + commandText);
        Command command = vimificationParser.parse(commandText);
        CommandResult result = null;
        try {
            if (command instanceof LogicCommand) {
                LogicCommand logicCommand = (LogicCommand) command;
                result = logicCommand.execute(logicTaskList, commandStack);
                storage.saveLogicTaskList(logicTaskList);
            } else if (command instanceof ViewCommand) {
                ViewCommand viewCommand = (ViewCommand) command;
                result = viewCommand.execute(viewTaskList);
            } else if (command instanceof MacroCommand) {
                MacroCommand macroCommand = (MacroCommand) command;
                result = macroCommand.execute(macroMap);
            } else if (command instanceof MiscCommand) {

            } else {
                LOGGER.warning("Unknown command type: " + command.getClass().getSimpleName());
                result = new CommandResult("Nothing happened");
            }
        } catch (RuntimeException ex) {
            result = new CommandResult(ex.getMessage());
        } catch (IOException ex) {
            result = new CommandResult(FILE_OPS_ERROR_MESSAGE);
        }
        // CommandResult result = command.execute(logicTaskList);
        // updateViewTaskList(command);
        // Only save when the result indicates that the task list should be saved
        return result;
    }

    // @Override
    // public ReadOnlyTaskPlanner getTaskList() {
    // return model.getTaskList();
    // }

    public ObservableList<Task> getViewTaskList() {
        return viewTaskList;
    }


    @Override
    public FilteredList<Task> getFilteredTaskList() {
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
