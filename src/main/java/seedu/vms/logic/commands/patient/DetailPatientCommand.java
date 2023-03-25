package seedu.vms.logic.commands.patient;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.patient.Patient;


/**
 * Command to show the details of a patient.
 */
public class DetailPatientCommand extends Command {
    private final Index index;


    /**
     * Constructs a {@code DetailPatientCommand}.
     */
    public DetailPatientCommand(Index index) {
        this.index = index;
    }


    @Override
    public CommandMessage execute(Model model) throws CommandException {
        IdData<Patient> patient = model
                .getPatientManager()
                .getMapView()
                .get(index.getZeroBased());
        if (patient == null) {
            throw new CommandException("The patient does not exist");
        }
        model.setDetailedPatient(patient);
        return new CommandMessage("Detailing patient");
    }
}
