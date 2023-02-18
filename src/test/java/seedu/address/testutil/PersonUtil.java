package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

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
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_TELEGRAM_HANDLE + person.getTelegramHandle().telegramHandle + " ");
        person.getGroupTags().stream().forEach(
            s -> sb.append(PREFIX_GROUP_TAG + s.tagName + " ")
        );
        person.getModuleTags().stream().forEach(
                s -> sb.append(PREFIX_MODULE_TAG + s.tagName + " ")
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
        descriptor.getTelegramHandle().ifPresent(telegramHandle -> sb.append(PREFIX_TELEGRAM_HANDLE)
                .append(telegramHandle.telegramHandle).append(" "));
        if (descriptor.getGroupTags().isPresent()) {
            Set<GroupTag> groupTags = descriptor.getGroupTags().get();
            if (groupTags.isEmpty()) {
                sb.append(PREFIX_GROUP_TAG);
            } else {
                groupTags.forEach(s -> sb.append(PREFIX_GROUP_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getModuleTags().isPresent()) {
            Set<ModuleTag> moduleTags = descriptor.getModuleTags().get();
            if (moduleTags.isEmpty()) {
                sb.append(PREFIX_MODULE_TAG);
            } else {
                moduleTags.forEach(s -> sb.append(PREFIX_MODULE_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
