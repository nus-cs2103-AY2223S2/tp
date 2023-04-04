package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static java.util.Objects.requireNonNull;

import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.event.UpcomingEventPredicate;

/**
 * List the next (or next few) upcoming {@code Event}.
 * Completed {@code Event} are not considered upcoming.
 */
public class ShowNextCommand extends Command {

    public static final String COMMAND_WORD = "next";
    public static final int SHOW_UPCOMING_COUNT_ONE = 1;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display upcoming events."
            + "\nCan be used without any parameters."
            + "\nParameter: COUNT (must be a positive integer)"
            + "\nExample: " + COMMAND_WORD + " 3";

    private final UpcomingEventPredicate predicate;

    public ShowNextCommand(UpcomingEventPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateUpcomingEventList(predicate);
        return new CommandResult(
                String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, model.getUpcomingEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowNextCommand // instanceof handles nulls
                && predicate.equals(((ShowNextCommand) other).predicate)); // state check
    }
}
