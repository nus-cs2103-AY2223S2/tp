package seedu.address.logic.commands.task.todo;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.todo.InternshipTodo;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a todo identified using it's displayed index from the todo list.
 */
public class DeleteTodoCommand extends Command {

    public static final String COMMAND_WORD = "delete todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified todo from the list of todo.\n"
            + "Deletes todo at the specified INDEX.\n"
            + "The index refers to the index number shown in the displayed todo list.\n"
            + "Parameters: INDEX (must be a positive integer 1, 2, 3, ...)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_TODO_SUCCESS = "Deleted todo: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteCommand to delete the specified {@code targetIndex} todo
     */
    public DeleteTodoCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternshipTodo> lastShownList = model.getFilteredTodoList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        InternshipTodo todoToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteTodo(todoToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_TODO_SUCCESS, todoToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTodoCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTodoCommand) other).targetIndex)); // state check
    }
}
