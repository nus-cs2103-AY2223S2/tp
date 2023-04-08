package ezschedule.logic.commands;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.event.Event;

/**
 * Deletes an {@code Event} identified using it's displayed index from the {@code Scheduler}.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: ";

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public String commandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        StringBuilder feedback = new StringBuilder();
        Collections.sort(targetIndexes); // sort index in order
        Collections.reverse(targetIndexes); // reverse index order to prevent exception when deleting
        model.clearRecent();
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(
                        String.format(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, targetIndex.getZeroBased() + 1));
            } else {
                model.addRecentCommand(this);
                Event eventToDelete = lastShownList.get(targetIndex.getZeroBased());
                model.deleteEvent(eventToDelete);
                model.addRecentEvent(eventToDelete);
                feedback.insert(0, eventToDelete.toString());
            }
        }
        feedback.insert(0, MESSAGE_DELETE_EVENT_SUCCESS);
        return new CommandResult(feedback.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
