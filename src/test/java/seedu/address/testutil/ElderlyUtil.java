package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddElderlyCommand;
import seedu.address.logic.commands.EditElderlyCommand;
import seedu.address.logic.commands.util.EditDescriptor;
import seedu.address.model.person.Elderly;

/**
 * A utility class for Elderly.
 */
public class ElderlyUtil {

    /**
     * Returns an add elderly command string for adding the {@code elderly}.
     */
    public static String getAddElderlyCommand(Elderly elderly) {
        return AddElderlyCommand.COMMAND_WORD + " " + getElderlyDetails(elderly);
    }

    /**
     * Returns the part of command string for the given {@code elderly}'s details.
     */
    public static String getElderlyDetails(Elderly elderly) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME).append(elderly.getName().fullName).append(" ");
        sb.append(PREFIX_PHONE).append(elderly.getPhone().value).append(" ");
        sb.append(PREFIX_EMAIL).append(elderly.getEmail().value).append(" ");
        sb.append(PREFIX_ADDRESS).append(elderly.getAddress().value).append(" ");
        sb.append(PREFIX_NRIC).append(elderly.getNric().value).append(" ");
        sb.append(PREFIX_BIRTH_DATE).append(elderly.getBirthDate().birthDate.toString()).append(" ");
        sb.append(PREFIX_REGION).append(elderly.getRegion()).append(" ");
        sb.append(PREFIX_RISK).append(elderly.getRiskLevel()).append(" ");
        elderly.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG).append(s.tagName).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns an edit elderly command string for adding the {@code volunteer}.
     */
    public static String getEditElderlyCommand(int index, EditDescriptor descriptor) {
        return EditElderlyCommand.COMMAND_WORD + " " + index
                + " " + TestUtil.getEditDescriptorDetails(descriptor);
    }
}
