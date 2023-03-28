package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Sorts and lists all internship application by any one of the following attributes: CompanyName,
 * JobTitle, Status, InterviewDate in ascending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of internship applications" +
            "by ascending order. At most one parameter can be included. If none of the parameters is specified, "
            + "it sorts by COMPANY_NAME\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME] "
            + "[" + PREFIX_JOB_TITLE + "JOB_TITLE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_DATE + "INTERVIEW_DATE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DATE;

    private final Prefix prefix;

    public SortCommand(Prefix prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        /*
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    */
        return true;
    }
}

