package vimification.logic.commands.logicCommands;

import vimification.logic.commands.Command;
import vimification.model.LogicTaskList;

public abstract class LogicCommand extends Command {
    protected static final String NOT_UNDOABLE_MESSAGE = "This command is not undoable.";
    protected static final String FINISHED_EXECUTION_MESSAGE =
            "This command has been executed. It cannot be executed again.";

    LogicCommand() {
        super(false);
    }
}
