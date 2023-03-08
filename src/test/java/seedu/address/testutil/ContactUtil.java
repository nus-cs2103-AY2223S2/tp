package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.NewContactCommand;
import seedu.address.model.contact.Contact;

/**
 * Utility class for a Contact.
 */
public class ContactUtil {

    /**
     * Returns an newContact command string for adding the {@code contact}.
     */
    public static String getNewContactCommand(Contact contact) {
        return NewContactCommand.COMMAND_WORD + " " + getContactDetails(contact);
    }

    /**
     * Returns the part of command string for the given {@code contact}'s details.
     */
    public static String getContactDetails(Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + contact.getName().fullName + " ");
        sb.append(PREFIX_PHONE + contact.getPhone().value + " ");
        return sb.toString();
    }
}
