package vimification.internal.command.logic;

import java.util.Objects;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Creates a new task and adds it to the list.
 */
public class AddCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "a";
    public static final String SUCCESS_MESSAGE = "A new task has been created.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The new task has been deleted.";

    private final Task addedTask;

    /**
     * Creates an {@code AddCommand} to add the specified {@code Task}.
     *
     * @param addedTask the task to be added
     */
    public AddCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        taskList.add(addedTask);
        commandStack.push(this);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.removeLast();
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddCommand)) {
            return false;
        }
        AddCommand otherCommand = (AddCommand) other;
        return Objects.equals(addedTask, otherCommand.addedTask);
    }
}
