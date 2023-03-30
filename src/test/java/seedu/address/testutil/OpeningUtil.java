package seedu.address.testutil;

import static seedu.ultron.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_KEYDATE;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;

import seedu.ultron.logic.commands.AddCommand;
import seedu.ultron.logic.commands.EditCommand.EditOpeningDescriptor;
import seedu.ultron.model.opening.Keydate;
import seedu.ultron.model.opening.Opening;

/**
 * A utility class for Opening.
 */
public class OpeningUtil {

    /**
     * Returns an add command string for adding the {@code opening}.
     */
    public static String getAddCommand(Opening opening) {
        return AddCommand.COMMAND_WORD + " " + getOpeningDetails(opening);
    }

    /**
     * Returns the part of command string for the given {@code opening}'s details.
     */
    public static String getOpeningDetails(Opening opening) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_POSITION + opening.getPosition().fullPosition + " ");
        sb.append(PREFIX_COMPANY + opening.getCompany().fullCompany + " ");
        sb.append(PREFIX_EMAIL + opening.getEmail().value + " ");
        sb.append(PREFIX_STATUS + opening.getStatus().fullStatus + " ");
        sb.append(PREFIX_REMARK + opening.getRemark().value + " ");
        opening.getKeydates().stream().forEach(
            s -> sb.append(PREFIX_KEYDATE + s.fullKey + "@" + s.fullDate + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditOpeningDescriptor}'s details.
     */
    public static String getEditOpeningDescriptorDetails(EditOpeningDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPosition().ifPresent(position -> sb.append(PREFIX_POSITION)
                .append(position.fullPosition).append(" "));
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.fullCompany)
                .append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.fullStatus).append(" "));
        if (descriptor.getKeydates().isPresent()) {
            List<Keydate> dates = descriptor.getKeydates().get();
            if (dates.isEmpty()) {
                sb.append(PREFIX_KEYDATE);
            } else {
                dates.forEach(s -> sb.append(PREFIX_KEYDATE).append(s.fullKey).append("@")
                        .append(s.fullDate).append(" "));
            }
        }
        return sb.toString();
    }
}
