package seedu.connectus.testutil;

import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.connectus.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

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
        if (person.getPhone().isPresent()) {
            sb.append(PREFIX_PHONE + String.valueOf(person.getPhone().get()) + " ");
        }
        if (person.getEmail().isPresent()) {
            sb.append(PREFIX_EMAIL + String.valueOf(person.getEmail().get()) + " ");
        }
        if (person.getAddress().isPresent()) {
            sb.append(PREFIX_ADDRESS + String.valueOf(person.getAddress().get()) + " ");
        }
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
