package seedu.event.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.event.commons.core.Messages;
import seedu.event.model.Model;
import seedu.event.model.event.StartTimeWithinDaysPredicate;

/**
 * Finds and lists all events in event book whose start time is within the days specified in the argument.
 */
public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose start time is within "
            + "the specified number of days and displays them as a list with index numbers.\n"
            + "Parameters: DAYS (must be a positive integer and below 1,000,000)\n"
            + "Example: " + COMMAND_WORD + " 3";

    private final StartTimeWithinDaysPredicate predicate;

    public RemindCommand(StartTimeWithinDaysPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && predicate.equals(((RemindCommand) other).predicate)); // state check
    }
}
