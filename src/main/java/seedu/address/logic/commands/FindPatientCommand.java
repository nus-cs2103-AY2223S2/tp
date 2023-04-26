package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.patient.PatientContainsKeywordsPredicate;

/**
 * Finds and lists all patients in address book matching the respective parameters.
 * Parameter matching is case-insensitive and substrings are matched.
 * At least one parameter is required
 * Tags are matched fully
 */
public class FindPatientCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "find-ptn";
    public static final String SHORTHAND_COMMAND_WORD = "fp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (short form: " + SHORTHAND_COMMAND_WORD + ")"
            + ": Finds a patient in the address book. "
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_DIAGNOSIS + "DIAGNOSIS] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "At least one of the parameters must be present. \n"
            + "Only one of each parameter (excluding " + PREFIX_TAG + "TAG) may be present. \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe";

    private final PatientContainsKeywordsPredicate predicate;

    public FindPatientCommand(PatientContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    public static String getMessageSuccess() {
        return Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPatientCommand // instanceof handles nulls
                && predicate.equals(((FindPatientCommand) other).predicate)); // state check
    }


}
