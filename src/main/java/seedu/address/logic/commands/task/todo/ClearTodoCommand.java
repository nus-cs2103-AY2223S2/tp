package seedu.address.logic.commands.task.todo;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.todo.InternshipTodo;

/**
 * Clears the address book.
 */
public class ClearTodoCommand extends Command {

    public static final String COMMAND_WORD = "clear todo";
    public static final String MESSAGE_SUCCESS = "All todos has been cleared!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<InternshipTodo> lastShownList = model.getFilteredTodoList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL);
        }

        model.clearTodo(new AddressBook());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
