package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Assigns a patient to a doctor in the address book.
 */
public class AssignPatientCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "assign-ptn";
    public static final String SHORTHAND_COMMAND_WORD = "asn";


    public static final String MESSAGE_USAGE = COMMAND_WORD + " (short form: " + SHORTHAND_COMMAND_WORD + ")"
            + ": Assigns patient identified by the patient index number used in the displayed patients list "
            + "with the doctor identified by the doctor index number used in the displayed doctor list.\n"
            + "Parameters: "
            + PREFIX_PATIENT + "PATIENT_INDEX (must be a positive integer) "
            + PREFIX_DOCTOR + "DOCTOR_INDEX (must be a positive integer) ";
    private static final String MESSAGE_ASSIGN_PATIENT_SUCCESS = "Assigned Patient %1s to Doctor %2s.";
    private static final String MESSAGE_PATIENT_ALREADY_ASSIGNED = "Patient %1s is already assigned to Doctor %2s.";
    private final Index patientIndex;
    private final Index doctorIndex;

    /**
     * @param patientIndex of the patient in the filtered patient list to assign
     * @param doctorIndex of the doctor in the filtered doctor list to assign to
     **/
    public AssignPatientCommand(Index patientIndex, Index doctorIndex) {
        this.patientIndex = patientIndex;
        this.doctorIndex = doctorIndex;
    }

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    //TODO: Implement tests that invoke this method
    public static String getMessageSuccess() {
        return MESSAGE_ASSIGN_PATIENT_SUCCESS;
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

        Patient patientToAssign = lastShownPatientList.get(patientIndex.getZeroBased());
        Doctor doctorToAssign = lastShownDoctorList.get(doctorIndex.getZeroBased());

        Doctor doctorWithAssign = createDoctorWithAssign(doctorToAssign, patientToAssign);
        Patient patientWithAssign = createPatientWithAssign(doctorToAssign, patientToAssign);

        model.setDoctor(doctorToAssign, doctorWithAssign);
        model.setPatient(patientToAssign, patientWithAssign);
        return new CommandResult(String.format(MESSAGE_ASSIGN_PATIENT_SUCCESS,
                patientToAssign.getName().getValue(),
                doctorToAssign.getName().getValue()), patientWithAssign);
    }

    private static Doctor createDoctorWithAssign(Doctor doctorToAssign, Patient patientToAssign)
            throws CommandException {
        assert doctorToAssign != null;
        assert patientToAssign != null;
        Set<Patient> patientsSet = doctorToAssign.getPatients();

        if (patientsSet.contains(patientToAssign)) {
            throw new CommandException(String.format(MESSAGE_PATIENT_ALREADY_ASSIGNED,
                    patientToAssign.getName().getValue(),
                    doctorToAssign.getName().getValue()));
        }

        patientsSet.add(patientToAssign);


        return new Doctor(doctorToAssign.getName(),
                doctorToAssign.getPhone(),
                doctorToAssign.getEmail(),
                doctorToAssign.getSpecialty(),
                doctorToAssign.getYoe(),
                doctorToAssign.getTags(),
                patientsSet);
    }

    private static Patient createPatientWithAssign(Doctor doctorToAssign, Patient patientToAssign)
            throws CommandException {
        assert doctorToAssign != null;
        assert patientToAssign != null;
        Set<Doctor> doctorsSet = patientToAssign.getDoctors();

        if (doctorsSet.contains(doctorToAssign)) {
            throw new CommandException(String.format(MESSAGE_PATIENT_ALREADY_ASSIGNED,
                    patientToAssign.getName().getValue(),
                    doctorToAssign.getName().getValue()));
        }

        doctorsSet.add(doctorToAssign);

        return new Patient(patientToAssign.getName(),
                patientToAssign.getPhone(),
                patientToAssign.getEmail(),
                patientToAssign.getHeight(),
                patientToAssign.getWeight(),
                patientToAssign.getDiagnosis(),
                patientToAssign.getStatus(),
                patientToAssign.getRemark(),
                patientToAssign.getTags(),
                doctorsSet);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignPatientCommand // instanceof handles nulls
                && patientIndex.equals(((AssignPatientCommand) other).patientIndex)
                && doctorIndex.equals(((AssignPatientCommand) other).doctorIndex)); // state check
    }
}
