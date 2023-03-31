package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_IS_DOCTOR;

import seedu.address.model.Model;

/**
 * Lists all doctors in the address book to the user.
 */
public class ListDoctorsCommand extends Command {

    public static final String COMMAND_WORD = "listDoctors";

    public static final String MESSAGE_SUCCESS = "Listed all doctors";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_IS_DOCTOR);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
