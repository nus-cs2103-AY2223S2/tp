package vimification.logic.commands;

import vimification.model.LogicTaskList;

public abstract class LogicCommand extends Command {
    protected static final String NOT_UNDOABLE_MESSAGE = "This command is not undoable.";
    protected static final String FINISHED_EXECUTION_MESSAGE =
            "This command has been executed. It cannot be executed again.";

    public abstract CommandResult execute(LogicTaskList taskList) throws CommandException;
}
