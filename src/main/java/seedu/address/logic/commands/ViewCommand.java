package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.TaskBookModel.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;
import seedu.address.model.TaskBookModel;

/**
 * Lists all tasks in the address book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model, TaskBookModel taskBookModel) {
        requireNonNull(model);
        taskBookModel.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
