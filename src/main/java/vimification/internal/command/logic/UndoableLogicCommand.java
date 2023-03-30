package vimification.internal.command.logic;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;

public abstract class UndoableLogicCommand extends LogicCommand {

    @Override
    public abstract CommandResult execute(LogicTaskList taskList, CommandStack commandStack);

    public abstract CommandResult undo(LogicTaskList taskList);
}
