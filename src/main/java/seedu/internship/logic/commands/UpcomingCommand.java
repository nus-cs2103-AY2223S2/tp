package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.model.Model.PREDICATE_SHOW_UPCOMING_INTERNSHIPS;

import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;


/**
 * Lists all internships that have deadlines in the upcoming week.
 */
public class UpcomingCommand extends Command {

    public static final String COMMAND_WORD = "upcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all internships with deadlines in the upcoming week\n"
            + "No fields required. Any fields will be ignored.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all internships with events or deadlines "
            + "in the upcoming week";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_UPCOMING_INTERNSHIPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
