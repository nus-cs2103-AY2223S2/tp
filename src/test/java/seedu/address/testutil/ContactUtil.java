//package seedu.address.testutil;
//
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//
//import seedu.address.logic.commands.AddContactCommand;
//import seedu.address.model.contact.Contact;
//
///**
// * A utility class for Contact.
// */
//public class ContactUtil {
//
//    /**
//     * Returns an add command string for adding the {@code contact}.
//     */
//    public static String getAddContactCommand(Contact contact) {
//        return AddContactCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
//                + getContactDetails(contact);
//    }
//
//    /**
//     * Returns the part of command string for the given {@code contact}'s details.
//     */
//    public static String getContactDetails(Contact contact) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(PREFIX_PHONE + contact.getPhone().value + " ");
//        sb.append(PREFIX_EMAIL + contact.getEmail().value);
//        return sb.toString();
//    }
//}
