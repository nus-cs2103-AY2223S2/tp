package seedu.event.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.event.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;

import seedu.event.commons.core.Messages;
import seedu.event.commons.core.index.Index;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.model.Model;
import seedu.event.model.event.Event;

/**
 * Unmarks an event identified using it's displayed index from the event book.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer and below 1,000,000)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_EVENT_SUCCESS = "Unmarked event: %1$s";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToUnmark = lastShownList.get(targetIndex.getZeroBased());
        model.unmarkEvent(eventToUnmark);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_UNMARK_EVENT_SUCCESS, eventToUnmark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkCommand) other).targetIndex)); // state check
    }
}
