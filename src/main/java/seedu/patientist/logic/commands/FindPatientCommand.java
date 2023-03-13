package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_PID;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.NameContainsKeywordsPredicate;
import seedu.patientist.model.person.patient.PidContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPatientCommand object
 */
public class FindPatientCommand extends Command {
    public static final String COMMAND_WORD = "findpat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose pid contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Keywords must be either name or pid.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PID + "PID] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PID + "A12345B";

    private final PidContainsKeywordsPredicate pidPredicate;
    private final NameContainsKeywordsPredicate namePredicate;

    /**
     * Constructor for FindPatientCommand with NameContainsKeywordsPredicate.
     * @param predicate The name keywords to check for.
     */
    public FindPatientCommand(NameContainsKeywordsPredicate predicate) {
        this.namePredicate = predicate;
        this.pidPredicate = null;
    }

    /**
     * Constructor for FindPatientCommand with PidContainsKeywordsPredicate.
     * @param predicate The pid keywords to check for.
     */
    public FindPatientCommand(PidContainsKeywordsPredicate predicate) {
        this.pidPredicate = predicate;
        this.namePredicate = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(pidPredicate == null ? namePredicate : pidPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof FindPatientCommand // instanceof handles nulls
                   && namePredicate.equals(((FindPatientCommand) other).namePredicate)// state check
                   && pidPredicate.equals(((FindPatientCommand) other).pidPredicate));
    }
}
