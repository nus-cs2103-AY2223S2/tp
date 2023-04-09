package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Objects;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.staff.StaffIdContainsKeywordsPredicate;
import seedu.patientist.model.person.staff.StaffNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPatientCommand object
 */
public class FindStaffCommand extends Command {
    public static final String COMMAND_WORD = "findstf";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all staff members whose id contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Keywords must be either name or id.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "ID_NUMBER] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A71265H";

    private final StaffIdContainsKeywordsPredicate staffIdPredicate;
    private final StaffNameContainsKeywordsPredicate staffNamePredicate;

    /**
     * Constructor for FindStaffCommand with {@code NameContainsKeywordsPredicate}.
     * @param namePredicate The name keywords to check for.
     */
    public FindStaffCommand(StaffNameContainsKeywordsPredicate namePredicate) {
        this.staffNamePredicate = namePredicate;
        this.staffIdPredicate = null;
    }

    /**
     * Constructor for FindStaffCommand with {@code IdContainsKeywordsPredicate}.
     * @param idPredicate The id keywords to check for.
     */
    public FindStaffCommand(StaffIdContainsKeywordsPredicate idPredicate) {
        this.staffIdPredicate = idPredicate;
        this.staffNamePredicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(staffIdPredicate == null ? staffNamePredicate : staffIdPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                -1, -1, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStaffCommand // instanceof handles nulls
                && Objects.equals(staffNamePredicate, ((FindStaffCommand) other).staffNamePredicate)// state check
                && Objects.equals(staffIdPredicate, ((FindStaffCommand) other).staffIdPredicate));
    }
}
