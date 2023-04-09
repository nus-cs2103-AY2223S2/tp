package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship identified by the index number used in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DELETE_INTERNSHIP_SUCCESS = "Deleted Internship: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToDelete = lastShownList.get(targetIndex.getZeroBased());

        // If Deleted Internship equals to the selected Internship
        if (internshipToDelete == model.getSelectedInternship()) {
            model.clearSelectedInternship();
        }
        model.deleteInternship(internshipToDelete);

        // After Deleting Internship , it is important to delete all the events associated.
        model.updateFilteredEventList(new EventByInternship(internshipToDelete));
        List<Event> eventListToDelete = model.getFilteredEventList();
        // Necessary to create an unmofifable array , as eventListToDelete() is getting updated with deletion
        Event[] eventListToDeleteArray = eventListToDelete.toArray(new Event[eventListToDelete.size()]);

        for (int i = 0; i < eventListToDeleteArray.length; i++) {
            Event e = eventListToDeleteArray[i];
            // Delete the Events associated with that iternship
            model.deleteEvent(e);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete),
                ResultType.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
