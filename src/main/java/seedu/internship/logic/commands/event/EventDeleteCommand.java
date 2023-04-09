package seedu.internship.logic.commands.event;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

/**
 * Deletes an event from the event catalogue.
 */
public class EventDeleteCommand extends EventCommand {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = EventCommand.COMMAND_WORD + " "
            + EventDeleteCommand.COMMAND_WORD + ": Deletes an event from the event catalogue.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer)\n"
            + "Example: " + EventCommand.COMMAND_WORD + " "
            + EventDeleteCommand.COMMAND_WORD + " "
            + "1";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Event deleted: %1$s";
    public static final String MESSAGE_NO_INTERNSHIP_SELECTED = "Select an internship before deleting an event.";

    private final Index targetIndex;

    /**
     * Creates an EventDeleteCommand to delete the specified event of index {@code Index}
     */
    public EventDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasSelectedInternship()) {
            throw new CommandException(MESSAGE_NO_INTERNSHIP_SELECTED);
        }
        Internship selectedInternship = model.getSelectedInternship();
        model.updateFilteredEventList(new EventByInternship(selectedInternship));
        List<Event> lastShownList = model.getFilteredEventList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deleteEvent(eventToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete) , ResultType.SHOW_INFO,
                selectedInternship, model.getFilteredEventList());

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((EventDeleteCommand) other).targetIndex)); // state check
    }

}
