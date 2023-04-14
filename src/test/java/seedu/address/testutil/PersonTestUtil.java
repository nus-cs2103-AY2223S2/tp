package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATOON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonTestUtil {

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
        sb.append(PREFIX_RANK + person.getRank().value + " ");
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_UNIT + person.getUnit().value + " ");
        sb.append(PREFIX_COMPANY + person.getCompany().value + " ");
        sb.append(PREFIX_PLATOON + person.getPlatoon().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
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
        descriptor.getRank().ifPresent(rank -> sb.append(PREFIX_RANK).append(rank.value).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getUnit().ifPresent(unit -> sb.append(PREFIX_UNIT).append(unit.value).append(" "));
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.value).append(" "));
        descriptor.getPlatoon().ifPresent(platoon -> sb.append(PREFIX_PLATOON).append(platoon.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));

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

    public static String getFilterDescriptorDetails(FilterDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        if (!descriptor.getRankValue().isBlank()) {
            sb.append(PREFIX_RANK).append(descriptor.getRankValue()).append(" ");
        }
        if (!descriptor.getNameValue().isBlank()) {
            sb.append(PREFIX_NAME).append(descriptor.getNameValue()).append(" ");
        }
        if (!descriptor.getUnitValue().isBlank()) {
            sb.append(PREFIX_UNIT).append(descriptor.getUnitValue()).append(" ");
        }
        if (!descriptor.getCompanyValue().isBlank()) {
            sb.append(PREFIX_COMPANY).append(descriptor.getCompanyValue()).append(" ");
        }
        if (!descriptor.getPlatoonValue().isBlank()) {
            sb.append(PREFIX_PLATOON).append(descriptor.getPlatoonValue()).append(" ");
        }
        if (!descriptor.getPhoneValue().isBlank()) {
            sb.append(PREFIX_PHONE).append(descriptor.getPhoneValue()).append(" ");
        }
        if (!descriptor.getEmailValue().isBlank()) {
            sb.append(PREFIX_EMAIL).append(descriptor.getEmailValue()).append(" ");
        }
        if (!descriptor.getAddressValue().isBlank()) {
            sb.append(PREFIX_ADDRESS).append(descriptor.getAddressValue()).append(" ");
        }
        if (!descriptor.getTagValues().isEmpty()) {
            descriptor.getTagValues().forEach(s -> sb.append(PREFIX_TAG).append(s).append(" "));
        }
        return sb.toString();
    }
}
