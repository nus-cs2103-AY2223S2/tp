package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.patientist.model.Model;
import seedu.patientist.model.person.IsPatientPredicate;

/**
 * Lists all patients in the patientist book to the user.
 */
public class ListPatientsCommand extends Command {
    public static final String COMMAND_WORD = "lspat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all patients\n"
           + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
           + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_SUCCESS = "Listed all patients";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new IsPatientPredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
