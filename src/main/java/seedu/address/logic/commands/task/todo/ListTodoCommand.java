package seedu.address.logic.commands.task.todo;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.todo.InternshipTodo;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TODO;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTodoCommand extends Command {

    public static final String COMMAND_WORD = "list todo";

    public static final String MESSAGE_SUCCESS = "Listed all todos";
    public static final String MESSAGE_NO_APPLICATIONS = "No todo at the moment";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODO);
        List<InternshipTodo> lastShownList = model.getFilteredTodoList();
        if (lastShownList.size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS);
        }
    }
}
