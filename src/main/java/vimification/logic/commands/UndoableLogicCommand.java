package vimification.logic.commands;

import vimification.model.LogicTaskList;

public abstract class UndoableLogicCommand extends LogicCommand {
    public abstract CommandResult undo(LogicTaskList taskList) throws CommandException;
}
