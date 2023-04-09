package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Objects;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.patient.PatientIdContainsKeywordsPredicate;
import seedu.patientist.model.person.patient.PatientNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPatientCommand object
 */
public class FindPatientCommand extends Command {
    public static final String COMMAND_WORD = "findpat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose id contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Keywords must be either name or id number.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "ID_NUMBER] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A12345B";

    private final PatientIdContainsKeywordsPredicate patientIdPredicate;
    private final PatientNameContainsKeywordsPredicate patientNamePredicate;

    /**
     * Constructor for FindPatientCommand with NameContainsKeywordsPredicate.
     * @param namePredicate The name keywords to check for.
     */
    public FindPatientCommand(PatientNameContainsKeywordsPredicate namePredicate) {
        this.patientNamePredicate = namePredicate;
        this.patientIdPredicate = null;
    }

    /**
     * Constructor for FindPatientCommand with PidContainsKeywordsPredicate.
     * @param idPredicate The id keywords to check for.
     */
    public FindPatientCommand(PatientIdContainsKeywordsPredicate idPredicate) {
        this.patientIdPredicate = idPredicate;
        this.patientNamePredicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(patientIdPredicate == null ? patientNamePredicate : patientIdPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                -1, -1, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof FindPatientCommand // instanceof handles nulls
                   && Objects.equals(patientNamePredicate, ((FindPatientCommand) other).patientNamePredicate)
                   && Objects.equals(patientIdPredicate, ((FindPatientCommand) other).patientIdPredicate));
    }
}
