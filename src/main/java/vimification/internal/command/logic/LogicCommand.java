package vimification.internal.command.logic;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;

public abstract class LogicCommand implements Command {
    public static final String NOT_UNDOABLE_MESSAGE = "This command is not undoable.";
    public static final String FINISHED_EXECUTION_MESSAGE =
            "This command has been executed. It cannot be executed again.";

    public abstract CommandResult execute(LogicTaskList taskList, CommandStack commandStack);
}
