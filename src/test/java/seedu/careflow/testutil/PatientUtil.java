package seedu.careflow.testutil;

import static seedu.careflow.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT_NUMBER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPatientDescriptor;
import seedu.careflow.model.patient.Patient;

/**
 * A utility class for Patient.
 */
public class PatientUtil {

    /**
     * Returns an add command string for adding the {@code person}.
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
        sb.append(PREFIX_EMAIL + patient.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + patient.getAddress().value + " ");
        sb.append(PREFIX_DOB + patient.getBirthDate().value + " ");
        sb.append(PREFIX_GENDER + patient.getGender().value + " ");
        sb.append(PREFIX_IC + patient.getIc().value + " ");
        sb.append(PREFIX_DRUG_ALLERGY + patient.getDrugAllergy().value + " ");
        sb.append(PREFIX_EMERGENCY_CONTACT_NUMBER + patient.getEmergencyContact().value + " ");
        return sb.toString();
    }

    //TODO the emergency contact can be null;
    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getDateOfBirth().ifPresent(address -> sb.append(PREFIX_DOB).append(address.value).append(" "));
        descriptor.getGender().ifPresent(address -> sb.append(PREFIX_GENDER).append(address.value).append(" "));
        descriptor.getIc().ifPresent(address -> sb.append(PREFIX_IC).append(address.value).append(" "));
        descriptor.getDrugAllergy().ifPresent(address -> sb.append(PREFIX_DRUG_ALLERGY).append(address.toString())
                .append(" "));
        descriptor.getEmergencyContact().ifPresent(address -> sb.append(PREFIX_EMERGENCY_CONTACT_NUMBER)
                .append(address.value).append(" "));

        return sb.toString();
    }
}
