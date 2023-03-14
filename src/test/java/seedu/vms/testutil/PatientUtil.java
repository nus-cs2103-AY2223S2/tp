package seedu.vms.testutil;

import java.util.Set;

import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.Patient;

/**
 * A utility class for Patient.
 */
public class PatientUtil {
    private static final String PREFIX_BLOODTYPE = CliSyntax.DELIMITER + CliSyntax.PREFIX_BLOODTYPE + " ";
    private static final String PREFIX_DOB = CliSyntax.DELIMITER + CliSyntax.PREFIX_DOB + " ";
    private static final String PREFIX_NAME = CliSyntax.DELIMITER + CliSyntax.PREFIX_NAME + " ";
    private static final String PREFIX_PHONE = CliSyntax.DELIMITER + CliSyntax.PREFIX_PHONE + " ";
    private static final String PREFIX_ALLERGY = CliSyntax.DELIMITER + CliSyntax.PREFIX_ALLERGY + " ";
    private static final String PREFIX_VACCINATION = CliSyntax.DELIMITER + CliSyntax.PREFIX_VACCINATION + " ";

    /**
     * Returns an add command string for adding the {@code patient}.
     */
    public static String getAddCommand(Patient patient) {
        return AddCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");
        sb.append(PREFIX_DOB + patient.getDob().value + " ");
        sb.append(PREFIX_BLOODTYPE + patient.getBloodType().toString() + " ");
        patient.getAllergy().stream().forEach(
                s -> sb.append(PREFIX_ALLERGY + s.toString() + " "));
        patient.getVaccine().stream().forEach(
                s -> sb.append(PREFIX_VACCINATION + s.toString() + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getDob().ifPresent(dob -> sb.append(PREFIX_DOB).append(dob.value).append(" "));
        descriptor.getBloodType()
                .ifPresent(bloodType -> sb.append(PREFIX_BLOODTYPE).append(bloodType.toString()).append(" "));
        if (descriptor.getAllergies().isPresent()) {
            Set<GroupName> allergies = descriptor.getAllergies().get();
            if (allergies.isEmpty()) {
                sb.append(PREFIX_ALLERGY);
            } else {
                allergies.forEach(s -> sb.append(PREFIX_ALLERGY).append(s.toString()).append(" "));
            }
        }
        if (descriptor.getVaccines().isPresent()) {
            Set<GroupName> vaccines = descriptor.getVaccines().get();
            if (vaccines.isEmpty()) {
                sb.append(PREFIX_VACCINATION);
            } else {
                vaccines.forEach(s -> sb.append(PREFIX_VACCINATION).append(s.toString()).append(" "));
            }
        }
        return sb.toString();
    }
}
