package teambuilder.testutil;

import static teambuilder.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static teambuilder.logic.parser.CliSyntax.PREFIX_EMAIL;
import static teambuilder.logic.parser.CliSyntax.PREFIX_MAJOR;
import static teambuilder.logic.parser.CliSyntax.PREFIX_NAME;
import static teambuilder.logic.parser.CliSyntax.PREFIX_PHONE;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TAG;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.Set;

import teambuilder.logic.commands.AddCommand;
import teambuilder.logic.commands.EditCommand.EditPersonDescriptor;
import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;

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
        sb.append(PREFIX_PHONE + person.getPhone().toString() + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().toString() + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().toString() + " ");
        sb.append(PREFIX_MAJOR + person.getMajor().toString() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getTeams().stream().forEach(
                s -> sb.append(PREFIX_TEAM + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address).append(" "));
        descriptor.getMajor().ifPresent(major -> sb.append(PREFIX_MAJOR).append(major).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getTeams().isPresent()) {
            Set<Tag> teams = descriptor.getTeams().get();
            if (teams.isEmpty()) {
                sb.append(PREFIX_TEAM).append(" ");
            } else {
                teams.forEach(s -> sb.append(PREFIX_TEAM).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
