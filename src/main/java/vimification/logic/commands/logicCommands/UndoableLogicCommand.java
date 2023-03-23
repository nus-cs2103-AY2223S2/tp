package vimification.logic.commands.logicCommands;

import vimification.logic.commands.CommandException;
import vimification.logic.commands.CommandResult;
import vimification.model.LogicTaskList;

public abstract class UndoableLogicCommand extends LogicCommand {
    public abstract CommandResult undo(LogicTaskList taskList) throws CommandException;
}
