package seedu.careflow.testutil;

import static seedu.careflow.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.careflow.logic.commands.patientcommands.AddCommand;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPersonDescriptor;
import seedu.careflow.model.person.Patient;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Patient patient) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPersonDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + patient.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + patient.getAddress().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        return sb.toString();
    }
}
