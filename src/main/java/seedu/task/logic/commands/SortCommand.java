package seedu.task.logic.commands;

import seedu.task.model.Model;

/**
 * Sorts the list.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts the list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SORT_SUCCESS_MESSAGE = "Task list sorted.";

    @Override
    public CommandResult execute(Model model) {
        model.sortTask();
        return new CommandResult(SORT_SUCCESS_MESSAGE, false, false);
    }
}
