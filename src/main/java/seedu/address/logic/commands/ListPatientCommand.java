package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.address.model.Model;

/**
 * Lists all patients in Docedex to the user.
 */
public class ListPatientCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "list-ptn";
    public static final String SHORTHAND_COMMAND_WORD = "lsp";

    private static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all patients in Docedex.\n"
            + "Example: " + COMMAND_WORD;
    private static final String MESSAGE_SUCCESS = "Listed all patients";

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    public static String getMessageSuccess() {
        return MESSAGE_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
