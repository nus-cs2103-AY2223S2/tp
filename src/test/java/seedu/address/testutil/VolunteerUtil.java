package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_VOLUNTEER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.util.EditVolunteerDescriptor;
import seedu.address.model.person.Volunteer;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Volunteer.
 */
public class VolunteerUtil {

    /**
     * Returns an add volunteer command string for adding the {@code volunteer}.
     */
    public static String getAddVolunteerCommand(Volunteer volunteer) {
        return AddVolunteerCommand.COMMAND_WORD + " " + getVolunteerDetails(volunteer);
    }

    /**
     * Returns the part of command string for the given {@code Volunteer}'s details.
     */
    public static String getVolunteerDetails(Volunteer volunteer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME).append(volunteer.getName().fullName).append(" ");
        sb.append(PREFIX_PHONE).append(volunteer.getPhone().value).append(" ");
        sb.append(PREFIX_EMAIL).append(volunteer.getEmail().value).append(" ");
        sb.append(PREFIX_ADDRESS).append(volunteer.getAddress().value).append(" ");
        sb.append(PREFIX_NRIC_VOLUNTEER).append(volunteer.getNric().value).append(" ");
        sb.append(PREFIX_AGE).append(volunteer.getAge().value).append(" ");
        volunteer.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG).append(s.tagName).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns an edit volunteer command string for adding the {@code volunteer}.
     */
    public static String getEditVolunteerCommand(int index, EditVolunteerDescriptor descriptor) {
        return EditVolunteerCommand.COMMAND_WORD + " " + index
                + " " + getEditVolunteerDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of command string for the given {@code EditVolunteerDescriptor}'s details.
     */
    public static String getEditVolunteerDescriptorDetails(EditVolunteerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getNric().ifPresent(nric -> sb.append(PREFIX_NRIC_VOLUNTEER).append(nric.value).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.value).append(" "));

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
