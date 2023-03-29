package vimification.internal.command;

import javafx.collections.ObservableList;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public interface Command {
    // /**
    // * Executes the command and returns the result message.
    // *
    // * @param model {@code Model} which the command should operate on.
    // * @return feedback message of the operation result for display
    // * @throws CommandException If an error occurs during command execution.
    // */
    public abstract CommandResult execute(LogicTaskList taskList) throws CommandException;

    public abstract ObservableList<Task> getViewTaskList();
}
