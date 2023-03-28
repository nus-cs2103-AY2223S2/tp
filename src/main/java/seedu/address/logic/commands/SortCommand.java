package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;

/**
 * Sorts and lists all internship application by any one of the following attributes: CompanyName,
 * JobTitle, Status, InterviewDate in ascending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of internship applications"
            + "by ascending order. Exactly one parameter should be specified. "
            + "If the value is not present, it will be placed at the end of the list.\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME] "
            + "[" + PREFIX_JOB_TITLE + "JOB_TITLE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_DATE + "INTERVIEW_DATE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DATE;

    public static final Prefix[] PREFIXES_SUPPORTED = {
        PREFIX_COMPANY_NAME, PREFIX_JOB_TITLE, PREFIX_STATUS, PREFIX_DATE
    };

    private final Comparator<InternshipApplication> comparator;

    /**
     * Creates a SortCommand with comparator to sort the list of internship application.
     *
     * @param comparator Comparator used to sort the list
     */
    public SortCommand(Comparator<InternshipApplication> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedFilteredInternshipList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW,
                        model.getSortedFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}

