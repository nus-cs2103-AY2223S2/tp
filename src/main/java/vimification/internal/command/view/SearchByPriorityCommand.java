package vimification.internal.command.view;

import vimification.model.task.Priority;

public class SearchByPriorityCommand extends SearchCommand {

    public static final String COMMAND_WORD = "s -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks that has the same priority as the input priority.\n"
            + "Parameters: PRIORITY (high, med or low)\n"
            + "Example: " + COMMAND_WORD + " high";

    public SearchByPriorityCommand(Priority priority) {
        super(task -> task.hasPriority(priority));
    }
}
