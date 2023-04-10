package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.task.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.task.model.Model;

/**
 * Lists all tasks in the task book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
