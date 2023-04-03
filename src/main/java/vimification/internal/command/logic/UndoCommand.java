package vimification.internal.command.logic;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;

/**
 * A special command that discards the modification made by other {@link UndoableLogicCommand}.
 * <p>
 * This command cannot be undone.
 */
public class UndoCommand extends LogicCommand {

    /**
     * Creates an instance of {@code UndoCommand}.
     */
    public UndoCommand() {}

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        return commandStack.pop().undo(taskList);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UndoCommand;
    }
}
