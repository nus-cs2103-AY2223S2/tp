package trackr.logic.commands.task;

import trackr.logic.commands.FindItemCommand;
import trackr.logic.commands.SortItemsCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;

/**
 * Sorts all task in task list by their statuses and deadlines.
 * Tasks not done with earlier deadlines would be displayed on top.
 */
public class SortTasksCommand extends SortItemsCommand {

    public static final String COMMAND_WORD = "sort_task";
    public static final String COMMAND_WORD_SHORTCUT = "sort_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks "
            + "based on their statuses and deadline.\n"
            + "Tasks that are not done and have the earliest deadlines would"
            + "be displayed on the top.\n"
            + "Example: " + COMMAND_WORD;

    public SortTasksCommand(TaskContainsKeywordsPredicate predicate) {
        super(predicate, ModelEnum.TASK);
    }
}
