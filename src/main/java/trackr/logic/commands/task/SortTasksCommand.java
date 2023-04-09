package trackr.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_CRITERIA;

import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.model.Model;
import trackr.model.task.SortTasksComparator;

/**
 * Sorts all task in task list using a criteria given.
 */
public class SortTasksCommand extends Command {
    public static final String COMMAND_WORD = "sort_task";
    public static final String COMMAND_WORD_SHORTCUT = "sort_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks "
            + "based on given criteria.\n"
            + "Parameters: " + "[" + PREFIX_CRITERIA + "CRITERIA] "
            + "Example: " + COMMAND_WORD + PREFIX_CRITERIA + "Deadline";

    public static final String MESSAGE_SUCCESS = "Tasks sorted!";

    private SortTasksComparator sortTasksComparator;

    /**
     * Creates a SortTasksCommand to sort the task list.
     *
     * @param sortTasksComparator The comparator used to compare 2 tasks according to a given criteria.
     */
    public SortTasksCommand(SortTasksComparator sortTasksComparator) {
        this.sortTasksComparator = sortTasksComparator;
    }

    /**
     * Sorts the filtered task list using the sortTasksComparator.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Success message of the sort operation for display.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredTaskList(sortTasksComparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTasksCommand); // instanceof handles nulls
    }
}
