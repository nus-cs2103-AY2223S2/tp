package vimification.internal.command.logic;

import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

public abstract class UndoableLogicCommand extends LogicCommand {
    public abstract CommandResult undo(LogicTaskList taskList) throws CommandException;
}
