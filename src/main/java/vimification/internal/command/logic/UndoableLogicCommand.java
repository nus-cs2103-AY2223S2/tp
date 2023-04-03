package vimification.internal.command.logic;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;

/**
 * Common class for {@link LogicCommand} that can be undone.
 */
public abstract class UndoableLogicCommand extends LogicCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract CommandResult execute(LogicTaskList taskList, CommandStack commandStack);

    /**
     * Undoes this command, and restores the application's data back to the state before this
     * command was executed.
     * <p>
     * Because of this restriction, not all commands can be undone.
     *
     * @param taskList the list that contains the application's data
     * @return a structure that contains relevant information about this undo operation
     */
    public abstract CommandResult undo(LogicTaskList taskList);
}
