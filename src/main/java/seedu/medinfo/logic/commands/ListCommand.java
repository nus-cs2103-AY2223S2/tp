package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.medinfo.commons.core.Messages;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;

/**
 * Lists all patients in MedInfo to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Executes the {@code ListCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(
                String.format(Messages.MESSAGE_ALL_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }
}
