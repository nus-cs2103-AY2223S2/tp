package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.model.Model;
import seedu.vms.model.patient.PatientManager;

/**
 * Clears the patient manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandMessage execute(Model model) {
        requireNonNull(model);
        model.setPatientManager(new PatientManager());
        return new CommandMessage(MESSAGE_SUCCESS);
    }
}
