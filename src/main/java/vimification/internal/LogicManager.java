package vimification.internal;

import java.io.IOException;
import java.util.logging.Logger;

import vimification.common.core.LogsCenter;
import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.internal.command.logic.LogicCommand;
import vimification.internal.command.macro.MacroCommand;
import vimification.internal.command.ui.UiCommand;
import vimification.internal.parser.ParserException;
import vimification.internal.parser.VimificationParser;
import vimification.model.CommandStack;
import vimification.model.MacroMap;
import vimification.model.TaskList;
import vimification.model.UiTaskList;
import vimification.storage.Storage;
import vimification.ui.MainScreen;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    private static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file";
    private static final String LIST_OPS_ERROR_MESSAGE = "Invalid operation";
    private static final Logger LOGGER = LogsCenter.getLogger(LogicManager.class);

    private TaskList taskList;
    private MacroMap macroMap;
    private CommandStack commandStack;
    private MainScreen mainScreen;
    private Storage storage;

    private final VimificationParser vimificationParser;

    /**
     * Constructs a {@code LogicManager}.
     */
    public LogicManager(
            TaskList taskList,
            MacroMap macroMap,
            CommandStack commandStack,
            Storage storage) {

        this.taskList = taskList;
        this.macroMap = macroMap;
        this.commandStack = commandStack;
        this.storage = storage;
        this.vimificationParser = VimificationParser.getInstance(macroMap);
    }

    @Override
    public CommandResult execute(String commandText) {
        LOGGER.info("[USER COMMAND] " + commandText);
        CommandResult result = null;
        try {
            Command command = vimificationParser.parse(commandText);
            if (command instanceof LogicCommand) {
                LogicCommand logicCommand = (LogicCommand) command;
                result = logicCommand.execute(taskList, commandStack);
                storage.saveTaskList(taskList);
            } else if (command instanceof UiCommand) {
                UiCommand uiCommand = (UiCommand) command;
                result = uiCommand.execute(mainScreen);
            } else if (command instanceof MacroCommand) {
                MacroCommand macroCommand = (MacroCommand) command;
                result = macroCommand.execute(macroMap);
                storage.saveMacroMap(macroMap);
            } else {
                LOGGER.warning("Unknown command type: " + command.getClass().getSimpleName());
                result = new CommandResult("Nothing happened!");
            }
        } catch (ParserException ex) {
            result = new CommandResult(ex.getMessage());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            result = new CommandResult(LIST_OPS_ERROR_MESSAGE);
        } catch (IOException ex) {
            result = new CommandResult(FILE_OPS_ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public UiTaskList getUiTaskList() {
        return taskList;
    }

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }
}
