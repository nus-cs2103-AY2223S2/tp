package vimification.logic.commands;

import vimification.model.LogicTaskList;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    private boolean isViewCommand;

    /**
     * Constructs a command with the given flag to indicate whether it is a view command.
     */
    protected Command(boolean isViewCommand) {
        this.isViewCommand = isViewCommand;
    }


    // /**
    // * Executes the command and returns the result message.
    // *
    // * @param model {@code Model} which the command should operate on.
    // * @return feedback message of the operation result for display
    // * @throws CommandException If an error occurs during command execution.
    // */
    public abstract CommandResult execute(LogicTaskList taskList) throws CommandException;

}
