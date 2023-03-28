package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.person.Volunteer;

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
        sb.append(PREFIX_NRIC).append(volunteer.getNric().value).append(" ");
        sb.append(PREFIX_BIRTH_DATE).append(volunteer.getBirthDate().birthDate.toString()).append(" ");
        sb.append(PREFIX_REGION).append(volunteer.getRegion()).append(" ");
        volunteer.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG).append(s.tagName).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns an edit volunteer command string for adding the {@code volunteer}.
     */
    public static String getEditVolunteerCommand(int index, EditDescriptor descriptor) {
        return EditVolunteerCommand.COMMAND_WORD + " " + index
                + " " + TestUtil.getEditDescriptorDetails(descriptor);
    }
}
