package seedu.address.testutil;

import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ApplicationCliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.AddApplicationCommand;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;

/**
 * A utility class for Application.
 */
public class ApplicationUtil {

    /**
     * Returns an add application command string for adding the {@code person}.
     */
    public static String getAddApplicationCommand(Application application) {
        return AddApplicationCommand.COMMAND_WORD + " " + getApplicationDetails(application);
    }

    /**
     * Returns the part of command string for the given {@code application}'s details.
     */
    public static String getApplicationDetails(Application application) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ROLE + application.getRole().roleApplied + " ");
        sb.append(PREFIX_COMPANY_NAME + application.getCompanyName().name + " ");
        sb.append(PREFIX_COMPANY_EMAIL + application.getCompanyEmail().value + " ");
        sb.append(PREFIX_STATUS + application.getStatus().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditApplicationDescriptor}'s details.
     */
    public static String getEditApplicationDescriptorDetails(EditApplicationDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.roleApplied).append(" "));
        descriptor.getCompanyName().ifPresent(companyName -> sb.append(PREFIX_COMPANY_NAME)
                .append(companyName.name).append(" "));
        descriptor.getCompanyEmail().ifPresent(companyEmail -> sb.append(PREFIX_COMPANY_EMAIL)
                .append(companyEmail.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.toString()).append(" "));
        return sb.toString();
    }
}
