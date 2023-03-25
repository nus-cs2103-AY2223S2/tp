package vimification.internal.commands.logic;

import vimification.internal.commands.CommandException;
import vimification.internal.commands.CommandResult;
import vimification.model.LogicTaskList;

public abstract class UndoableLogicCommand extends LogicCommand {
    public abstract CommandResult undo(LogicTaskList taskList) throws CommandException;
}
