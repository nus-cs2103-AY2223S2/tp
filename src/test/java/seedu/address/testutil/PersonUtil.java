package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Subject;
import seedu.address.model.tag.Tag;

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
        person.getOptionalPhone().map(Phone::toString).ifPresent(phone -> sb.append(PREFIX_PHONE + phone + " "));
        person.getOptionalEmail().map(Email::toString).ifPresent(email -> sb.append(PREFIX_EMAIL + email + " "));
        person.getOptionalAddress().map(Address::toString)
                .ifPresent(address -> sb.append(PREFIX_ADDRESS + address + " "));
        person.getOptionalRemark().map(Remark::toString).ifPresent(remark -> sb.append(PREFIX_REMARK + remark + " "));
        person.getSubjects().stream().forEach(
                s -> sb.append(PREFIX_SUBJECT + s.subjectName + " ")
        );
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
        descriptor.getPhone().orElseGet(Optional::empty)
                .ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().orElseGet(Optional::empty)
                .ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().orElseGet(Optional::empty)
                .ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getRemark().orElseGet(Optional::empty)
                .ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append("  "));
        if (descriptor.getSubjects().isPresent()) {
            Set<Subject> subjects = descriptor.getSubjects().get();
            if (subjects.isEmpty()) {
                sb.append(PREFIX_SUBJECT + " ");
            } else {
                subjects.forEach(s -> sb.append(PREFIX_SUBJECT).append(s.subjectName).append(" "));
            }
        }

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
