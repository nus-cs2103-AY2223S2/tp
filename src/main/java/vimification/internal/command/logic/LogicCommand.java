package vimification.internal.command.logic;

import vimification.internal.command.Command;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;

/**
 * Common class for {@link Command} that modifies the application's data.
 */
public abstract class LogicCommand implements Command {

    public static final String ALREADY_EXECUTED_MESSAGE =
            "This command has been executed. It cannot be executed again.";

    /**
     * Executes this command.
     *
     * @param taskList the list that contains the application's data
     * @param commandStack the stack of command, used by {@link UndoCommand}
     * @return a structure that contains relevant information about the execution of this command
     */
    public abstract CommandResult execute(LogicTaskList taskList, CommandStack commandStack);
}
