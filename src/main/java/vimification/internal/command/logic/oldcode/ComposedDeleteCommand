package vimification.internal.command.logic;

import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

public class ComposedDeleteCommand extends UndoableLogicCommand {

    private final UndoableLogicCommand[] commands;

    private ComposedDeleteCommand(UndoableLogicCommand... commands) {
        this.commands = commands;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        CommandResult[] results = new CommandResult[commands.length];
        for (int i = 0; i < commands.length; i++) {
            // I have to use for loop. lambda does not pair well with checked exception
            // Damn it
            CommandResult result = commands[i].execute(taskList);
            results[i] = result;
        }
        return results[0]; // for now
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        CommandResult[] results = new CommandResult[commands.length];
        for (int i = 0; i < commands.length; i++) {
            // I have to use for loop. lambda does not pair well with checked exception
            // Damn it
            CommandResult result = commands[i].undo(taskList);
            results[i] = result;
        }
        return results[0]; // for now
    }

}
