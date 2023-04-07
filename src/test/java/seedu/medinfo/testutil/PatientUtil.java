package seedu.medinfo.testutil;

import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_DISCHARGE;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.medinfo.logic.commands.AddCommand;
import seedu.medinfo.logic.commands.EditCommand;
import seedu.medinfo.model.patient.Patient;


/**
 * A utility class for Patient.
 */
public class PatientUtil {

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
        sb.append(PREFIX_NRIC + patient.getNric().value + " ");
        sb.append(PREFIX_STATUS + patient.getStatus().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditCommand.EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getNric().ifPresent(nric -> sb.append(PREFIX_NRIC).append(nric.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.value).append(" "));
        return sb.toString();
    }
}
