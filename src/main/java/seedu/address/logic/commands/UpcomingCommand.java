package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_INTERNSHIPS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all internships that have deadlines in the upcoming week.
 */
public class UpcomingCommand extends Command {

    public static final String COMMAND_WORD = "upcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all internships with deadlines in the upcoming week\n"
            + "No parameters required. Any parameters will be ignored.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all internship entries with events or deadlines "
            + "in the following week";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_UPCOMING_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
