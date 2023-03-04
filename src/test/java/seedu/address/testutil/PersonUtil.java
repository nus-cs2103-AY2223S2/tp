package seedu.address.testutil;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
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
        sb.append(Prefix.NAME + person.getName().value + " ");
        sb.append(Prefix.PHONE + person.getPhone().value + " ");
        sb.append(Prefix.EMAIL + person.getEmail().value + " ");
        sb.append(Prefix.ADDRESS + person.getAddress().value + " ");
        sb.append(Prefix.TELEGRAM_HANDLE + person.getTelegramHandle().value + " ");
        person.getImmutableGroupTags().stream().forEach(
            s -> sb.append(Prefix.GROUP_TAG + s.tagName + " ")
        );
        person.getImmutableModuleTags().stream().forEach(
                s -> sb.append(Prefix.MODULE_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(Prefix.NAME).append(name.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(Prefix.PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(Prefix.EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(Prefix.ADDRESS).append(address.value).append(" "));
        descriptor.getTelegramHandle().ifPresent(telegramHandle -> sb.append(Prefix.TELEGRAM_HANDLE)
                .append(telegramHandle.value).append(" "));
        if (descriptor.getGroupTags().isPresent()) {
            Set<GroupTag> groupTags = descriptor.getGroupTags().get();
            if (groupTags.isEmpty()) {
                sb.append(Prefix.GROUP_TAG);
            } else {
                groupTags.forEach(s -> sb.append(Prefix.GROUP_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getModuleTags().isPresent()) {
            Set<ModuleTag> moduleTags = descriptor.getModuleTags().get();
            if (moduleTags.isEmpty()) {
                sb.append(Prefix.MODULE_TAG);
            } else {
                moduleTags.forEach(s -> sb.append(Prefix.MODULE_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Checks whether the users' information are the same.
     * @param firstUser
     * @param secondUser
     * @return boolean.
     */
    public static boolean isSameUserAndUserStub(User firstUser, User secondUser) {
        return firstUser.getName().equals(secondUser.getName())
                && firstUser.getEmail().equals(secondUser.getEmail())
                && firstUser.getPhone().equals(secondUser.getPhone())
                && firstUser.getTelegramHandle().equals(secondUser.getTelegramHandle())
                && firstUser.getImmutableModuleTags().equals(secondUser.getImmutableModuleTags());
    }
}
