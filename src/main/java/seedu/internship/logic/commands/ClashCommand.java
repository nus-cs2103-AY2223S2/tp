package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * Shows all event pairs with clashing timings in TinS.
 */
public class ClashCommand extends Command {

    public static final String COMMAND_WORD = "clash";
    public static final String MESSAGE_CLASH_INTERNSHIP_SUCCESS = "All clashing Internships listed.";

    public List<List<Event>> clashingEvents;
    public List<Event>

    public ClashCommand() {
        clashingEvents = new ArrayList<>();

    }
    public

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> eventList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToDelete = lastShownList.get(targetIndex.getZeroBased());

        // If Deleted Internship equals to the selected Internship
        if (internshipToDelete == model.getSelectedInternship()) {
            model.clearSelectedInternship();
        }
        model.deleteInternship(internshipToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, internshipToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
