package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import seedu.address.model.Model;

/**
 * Lists all doctors in Docedex to the user.
 */
public class ListDoctorCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "list-doc";
    public static final String SHORTHAND_COMMAND_WORD = "lsd";

    private static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all doctors in Docedex.\n"
            + "Example: " + COMMAND_WORD;

    private static final String MESSAGE_SUCCESS = "Listed all doctors";

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    public static String getMessageSuccess() {
        return MESSAGE_SUCCESS;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
