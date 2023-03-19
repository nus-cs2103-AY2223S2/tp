package trackr.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.model.Model;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list_task";
    public static final String COMMAND_WORD_SHORTCUT = "list_t";


    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
