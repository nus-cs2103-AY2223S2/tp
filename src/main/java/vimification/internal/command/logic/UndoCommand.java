package vimification.internal.command.logic;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;

public class UndoCommand extends LogicCommand {

    public UndoCommand() {}

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        return commandStack.pop().undo(taskList);
    }
}
