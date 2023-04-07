package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Module;
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
        person.getOptionalEducation().map(Education::toString)
                .ifPresent(education -> sb.append(PREFIX_EDUCATION + education + " "));
        person.getOptionalRemark().map(Remark::toString).ifPresent(remark -> sb.append(PREFIX_REMARK + remark + " "));
        person.getOptionalTelegram().map(Telegram::toString)
                .ifPresent(telegram -> sb.append(PREFIX_TELEGRAM + telegram + " "));
        person.getModules().stream().forEach(
                s -> sb.append(PREFIX_MODULE + s.moduleName + " ")
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
        descriptor.getEducation().orElseGet(Optional::empty)
                .ifPresent(education -> sb.append(PREFIX_EDUCATION).append(education.value).append(" "));
        descriptor.getRemark().orElseGet(Optional::empty)
                .ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append("  "));
        descriptor.getTelegram().orElseGet(Optional::empty)
                .ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.value).append("  "));
        if (descriptor.getModules().isPresent()) {
            Set<Module> modules = descriptor.getModules().get();
            if (modules.isEmpty()) {
                sb.append(PREFIX_MODULE + " ");
            } else {
                modules.forEach(s -> sb.append(PREFIX_MODULE).append(s.moduleName).append(" "));
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
