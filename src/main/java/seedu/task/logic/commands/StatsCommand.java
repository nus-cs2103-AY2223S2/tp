package seedu.task.logic.commands;

import seedu.task.model.Model;
import seedu.task.model.tracker.TaskByTagTracker;

/**
 * Generates statistics based on tags.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Prints summary information.\n"
            + "Example: " + COMMAND_WORD;

    public static final String STATS_SUCCESS_MESSAGE = "Here is your requested data:\n";

    @Override
    public CommandResult execute(Model model) {
        String output = TaskByTagTracker.sort(model);
        return new CommandResult(STATS_SUCCESS_MESSAGE + output);
    }
}
