package trackr.logic.commands.task;

import static java.util.Objects.requireNonNull;

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
            + "based on their statuses and deadline.\n"
            + "Tasks that are not done and have the earliest deadlines would"
            + "be displayed on the top.\n"
            + "Example: " + COMMAND_WORD;

    private SortTasksComparator sortTasksComparator;

    public SortTasksCommand(SortTasksComparator sortTasksComparator) {
        this.sortTasksComparator = sortTasksComparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedTaskList(sortTasksComparator);
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_SORTED));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTasksCommand); // instanceof handles nulls
    }
}
