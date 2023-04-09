package trackr.logic.commands.task;

import trackr.logic.commands.FindItemCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;

/**
 * Finds and lists all task in task list whose description contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTaskCommand extends FindItemCommand<Task> {

    public static final String COMMAND_WORD = "find_task";
    public static final String COMMAND_WORD_SHORTCUT = "find_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " flour buy";

    /**
     * Creates an FindTaskCommand to find all {@code Task} that match the predicate.
     *
     * @param predicate The predicate to find the tasks with.
     */
    public FindTaskCommand(TaskContainsKeywordsPredicate predicate) {
        super(predicate, ModelEnum.TASK);
    }
}
