package seedu.vms.testutil;

import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.model.patient.Patient;

/**
 * A utility class for Patient.
 */
public class PatientUtil {
    private static final String PREFIX_NAME = CliSyntax.DELIMITER + CliSyntax.PREFIX_NAME + " ";
    private static final String PREFIX_PHONE = CliSyntax.DELIMITER + CliSyntax.PREFIX_PHONE + " ";

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
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        return sb.toString();
    }
}
