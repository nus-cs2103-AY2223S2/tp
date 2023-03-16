package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC_ELDERLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddElderlyCommand;
import seedu.address.logic.commands.EditElderlyCommand;
import seedu.address.logic.commands.util.EditElderlyDescriptor;
import seedu.address.model.person.Elderly;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_NRIC_ELDERLY).append(elderly.getNric().value).append(" ");
        sb.append(PREFIX_AGE).append(elderly.getAge().value).append(" ");
        sb.append(PREFIX_RISK).append(elderly.getRiskLevel()).append(" ");
        elderly.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG).append(s.tagName).append(" ")
        );
        return sb.toString();
    }

    /**
     * Returns an edit elderly command string for adding the {@code volunteer}.
     */
    public static String getEditElderlyCommand(int index, EditElderlyDescriptor descriptor) {
        return EditElderlyCommand.COMMAND_WORD + " " + index
                + " " + getEditElderlyDescriptorDetails(descriptor);
    }


    /**
     * Returns the part of command string for the given {@code EditElderlyDescriptor}'s details.
     */
    public static String getEditElderlyDescriptorDetails(EditElderlyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getNric().ifPresent(nric -> sb.append(PREFIX_NRIC_ELDERLY).append(nric.value).append(" "));
        descriptor.getAge().ifPresent(age -> sb.append(PREFIX_AGE).append(age.value).append(" "));
        descriptor.getRiskLevel().ifPresent(riskLevel ->
                sb.append(PREFIX_RISK).append(riskLevel).append(" "));

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
