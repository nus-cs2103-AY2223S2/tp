package vimification.internal;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import vimification.commons.core.LogsCenter;
import vimification.internal.command.Command;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.internal.command.logic.LogicCommand;
import vimification.internal.command.macro.MacroCommand;
import vimification.internal.command.ui.UiCommand;
import vimification.internal.parser.ParserException;
import vimification.internal.parser.VimificationParser;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.MacroMap;
import vimification.model.TaskListRef;
import vimification.model.UiTaskList;
import vimification.model.task.Task;
import vimification.storage.Storage;
import vimification.ui.MainScreen;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    private static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file";
    private static final String LIST_OPS_ERROR_MESSAGE = "Invalid operation";
    private static final Logger LOGGER = LogsCenter.getLogger(LogicManager.class);

    private LogicTaskList logicTaskList;
    private UiTaskList uiTaskList;
    private MacroMap macroMap;
    private CommandStack commandStack;
    private MainScreen mainScreen;
    private Storage storage;
    private TaskListRef ref;

    private final VimificationParser vimificationParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(
            TaskListRef ref,
            MacroMap macroMap,
            CommandStack commandStack,
            Storage storage) {
        List<Task> taskList = ref.getTaskList();
        ObservableList<Task> observableTaskList = FXCollections.observableList(taskList);
        FilteredList<Task> filteredTaskList = new FilteredList<>(observableTaskList);
        SortedList<Task> sortedTaskList = new SortedList<>(filteredTaskList);
        ref.setTaskList(observableTaskList);

        this.ref = ref;
        this.macroMap = macroMap;
        this.commandStack = commandStack;
        this.storage = storage;

        this.logicTaskList = new LogicTaskList(ref);
        this.uiTaskList = new UiTaskList(observableTaskList, filteredTaskList, sortedTaskList, ref);
        this.vimificationParser = VimificationParser.getInstance(macroMap);
    }

    @Override
    public CommandResult execute(String commandText) {
        LOGGER.info("[USER COMMAND] " + commandText);
        Command command = vimificationParser.parse(commandText);
        CommandResult result = null;
        try {
            if (command instanceof LogicCommand) {
                LogicCommand logicCommand = (LogicCommand) command;
                result = logicCommand.execute(logicTaskList, commandStack);
                storage.saveTaskListRef(ref);
            } else if (command instanceof UiCommand) {
                UiCommand uiCommand = (UiCommand) command;
                result = uiCommand.execute(mainScreen);
            } else if (command instanceof MacroCommand) {
                MacroCommand macroCommand = (MacroCommand) command;
                result = macroCommand.execute(macroMap);
                storage.saveMacroMap(macroMap);
            } else {
                LOGGER.warning("Unknown command type: " + command.getClass().getSimpleName());
                result = new CommandResult("Nothing happened");
            }
        } catch (ParserException ex) {
            result = new CommandResult(LIST_OPS_ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            result = new CommandResult(LIST_OPS_ERROR_MESSAGE);
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

    @Override
    public UiTaskList getUiTaskList() {
        return uiTaskList;
    }

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
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
