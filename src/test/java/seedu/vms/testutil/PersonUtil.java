package seedu.vms.testutil;

import seedu.vms.logic.parser.CliSyntax;

import java.util.Set;

import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.logic.commands.patient.EditCommand.EditPersonDescriptor;
import seedu.vms.model.person.Person;
import seedu.vms.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {
    private static final String PREFIX_ADDRESS = CliSyntax.DELIMITER + CliSyntax.PREFIX_ADDRESS + " ";
    private static final String PREFIX_EMAIL = CliSyntax.DELIMITER + CliSyntax.PREFIX_EMAIL + " ";
    private static final String PREFIX_NAME = CliSyntax.DELIMITER + CliSyntax.PREFIX_NAME + " ";
    private static final String PREFIX_PHONE = CliSyntax.DELIMITER + CliSyntax.PREFIX_PHONE + " ";
    private static final String PREFIX_TAG = CliSyntax.DELIMITER + CliSyntax.PREFIX_TAG + " ";

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
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
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
