package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Lists all internships that have deadlines in the upcoming week.
 */
public class UpcomingCommand extends Command {

    public static final String COMMAND_WORD = "upcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all internships with deadlines in the upcoming week\n"
            + "No parameters required\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEW_INTERNSHIP_SUCCESS = "Viewed Internship: %1$s";

    public UpcomingCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        //Checks for a valid index
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        //Gets the internship to view
        Internship internshipToView = lastShownList.get(targetIndex.getZeroBased());

        //Functionality of the view internship command
        model.updateSelectedInternship(internshipToView);
        return new CommandResult(String.format(MESSAGE_VIEW_INTERNSHIP_SUCCESS, internshipToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpcomingCommand // instanceof handles nulls
                && targetIndex.equals(((UpcomingCommand) other).targetIndex)); // state check
    }
}
