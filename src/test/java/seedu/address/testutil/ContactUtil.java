package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.model.contact.Contact;

/**
 * A utility class for Contact.
 */
public class ContactUtil {

    /**
     * Returns an add_contact command string for adding the {@code contact}.
     */
    public static String getAddContactCommandsArguments(Contact contact) {
        return getContactDetails(contact);
    }

    /**
     * Returns the part of command string for the given {@code contact}'s details.
     */
    public static String getContactDetails(Contact contact) {
        return PREFIX_PHONE + contact.getPhone().value + " "
                + PREFIX_EMAIL + contact.getEmail().value;
    }

    /**
     * Returns the part of command string for the given {@code EditContactDescriptor}'s details.
     */
    public static String getEditContactDescriptorDetails(EditContactCommand.EditContactDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPhone().ifPresent(phone ->
                sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email ->
                sb.append(PREFIX_EMAIL).append(email.value));
        return sb.toString();
    }
}
