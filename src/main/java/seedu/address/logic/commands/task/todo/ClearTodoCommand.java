package seedu.address.logic.commands.task.todo;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.TodoList;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.InternshipTodo;

/**
 * Clears the todo list.
 */
public class ClearTodoCommand extends Command {

    public static final String COMMAND_WORD = "clear_todo";
    public static final String MESSAGE_SUCCESS = "All todos have been cleared!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";

    private static final TaskType type = TaskType.TODO;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<InternshipTodo> lastShownList = model.getFilteredTodoList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_NULL, type);
        }

        model.clearTodo(new TodoList());

        return new CommandResult(MESSAGE_SUCCESS, type);
    }
}
