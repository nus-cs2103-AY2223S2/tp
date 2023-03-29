package seedu.address.logic.commands.task.todo;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TODO;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.InternshipTodo;
/**
 * Lists all todo applications in the todo list to the user.
 */
public class ListTodoCommand extends Command {

    public static final String COMMAND_WORD = "list_todo";
    public static final String MESSAGE_SUCCESS = "Listed all todos";
    public static final String MESSAGE_NO_APPLICATIONS = "No todo at the moment";

    private static final TaskType type = TaskType.TODO;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODO);
        List<InternshipTodo> lastShownList = model.getFilteredTodoList();
        if (lastShownList.size() > 0) {
            return new CommandResult(MESSAGE_SUCCESS, type);
        } else {
            return new CommandResult(MESSAGE_NO_APPLICATIONS, type);
        }
    }
}
