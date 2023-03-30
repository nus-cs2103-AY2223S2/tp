package trackr.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_CRITERIA;

import trackr.commons.core.Messages;
import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.model.Model;
import trackr.model.task.SortTasksComparator;

/**
 * Sorts all task in task list by their statuses and deadlines.
 * Tasks not done with earlier deadlines would be displayed on top.
 */
public class SortTasksCommand extends Command {
    public static final String COMMAND_WORD = "sort_task";
    public static final String COMMAND_WORD_SHORTCUT = "sort_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks "
            + "based on given criteria.\n"
            + "Parameters: " + "[" + PREFIX_CRITERIA + "TASK NAME] "
            + "Example: " + COMMAND_WORD + PREFIX_CRITERIA + "Deadline";

    private SortTasksComparator sortTasksComparator;

    public SortTasksCommand(SortTasksComparator sortTasksComparator) {
        this.sortTasksComparator = sortTasksComparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredTaskList(sortTasksComparator);
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_SORTED));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTasksCommand); // instanceof handles nulls
    }
}
