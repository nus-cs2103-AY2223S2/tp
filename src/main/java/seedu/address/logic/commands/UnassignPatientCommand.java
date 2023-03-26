package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Unassigns a patient to a doctor in the address book.
 */
public class UnassignPatientCommand extends Command {

    public static final String COMMAND_WORD = "unassign-ptn";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the assignment of "
            + "patient identified by the patient index number used in the displayed patients list "
            + "with the doctor identified by the doctor index number used in the displayed doctor list.\n"
            + "Parameters: "
            + "PATIENT_INDEX (must be a positive integer) "
            + "DOCTOR_INDEX (must be a positive integer) ";
    public static final String MESSAGE_UNASSIGN_PATIENT_SUCCESS = "Unassigned Patient %1s to Doctor %2s.";
    public static final String MESSAGE_PATIENT_NOT_ASSIGNED = "Patient %1s is not assigned to Doctor %2s.";
    private final Index patientIndex;
    private final Index doctorIndex;

    /**
     * @param patientIndex of the patient in the filtered patient list to unassign
     * @param doctorIndex of the doctor in the filtered doctor list to unassign from
     **/
    public UnassignPatientCommand(Index patientIndex, Index doctorIndex) {
        this.patientIndex = patientIndex;
        this.doctorIndex = doctorIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownDoctorList = model.getFilteredDoctorList();
        List<Patient> lastShownPatientList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownPatientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        if (doctorIndex.getZeroBased() >= lastShownDoctorList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Patient patientToUnassign = lastShownPatientList.get(patientIndex.getZeroBased());
        Doctor doctorToUnassign = lastShownDoctorList.get(doctorIndex.getZeroBased());

        Doctor doctorWithoutAssign = createDoctorWithoutAssign(doctorToUnassign, patientToUnassign);

        model.setDoctor(doctorToUnassign, doctorWithoutAssign);
        return new CommandResult(String.format(MESSAGE_UNASSIGN_PATIENT_SUCCESS,
                patientToUnassign.getName().fullName,
                doctorToUnassign.getName().fullName));
    }

    private static Doctor createDoctorWithoutAssign(Doctor doctorToUnassign, Patient patientToUnassign)
            throws CommandException {
        assert doctorToUnassign != null;
        assert patientToUnassign != null;

        Set<Patient> patientsSet = doctorToUnassign.getPatients();

        if (!patientsSet.contains(patientToUnassign)) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_ASSIGNED,
                    patientToUnassign.getName().fullName,
                    doctorToUnassign.getName().fullName));
        }

        patientsSet.remove(patientToUnassign);

        return new Doctor(doctorToUnassign.getName(),
                doctorToUnassign.getPhone(),
                doctorToUnassign.getEmail(),
                doctorToUnassign.getSpecialty(),
                doctorToUnassign.getYoe(),
                doctorToUnassign.getTags(),
                patientsSet);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignPatientCommand // instanceof handles nulls
                && patientIndex.equals(((UnassignPatientCommand) other).patientIndex)
                && doctorIndex.equals(((UnassignPatientCommand) other).doctorIndex)); // state check
    }
}
