package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_IS_PATIENT;

import seedu.address.model.Model;

/**
 * Lists all doctors in the address book to the user.
 */
public class ListPatientsCommand extends Command {

    public static final String COMMAND_WORD = "listPatients";

    public static final String MESSAGE_SUCCESS = "Listed all patients";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_IS_PATIENT);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
