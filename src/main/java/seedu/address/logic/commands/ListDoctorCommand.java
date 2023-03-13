package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import seedu.address.model.Model;

/**
 * Lists all doctors in Docedex to the user.
 */
public class ListDoctorCommand extends Command {

    public static final String COMMAND_WORD = "list-doc";

    public static final String MESSAGE_SUCCESS = "Listed all doctors";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
