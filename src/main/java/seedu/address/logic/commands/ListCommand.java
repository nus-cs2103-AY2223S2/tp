package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Lists all patients in MedInfo to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(
                String.format(Messages.MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }
}
