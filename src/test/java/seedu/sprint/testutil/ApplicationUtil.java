package seedu.sprint.testutil;

import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_EMAIL;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.sprint.logic.commands.AddApplicationCommand;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.tag.Tag;
import seedu.sprint.model.task.Task;


/**
 * A utility class for Application.
 */
public class ApplicationUtil {

    /**
     * Returns an add application command string for adding the {@code application}.
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
        if (application.hasTask()) {
            sb.append(PREFIX_DEADLINE + application.getTask().getDeadline().toDisplayString() + " ");
            sb.append(PREFIX_DESCRIPTION + application.getTask().getDescription().toString() + " ");
        }
        application.getTags().stream().forEach(
                tag -> sb.append(PREFIX_TAG + tag.toString() + " ")
        );
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
        if (descriptor.getTask().isPresent()) {
            Task task = descriptor.getTask().get();
            sb.append(PREFIX_DESCRIPTION).append(task.getDescription().toString()).append(" ");
            sb.append(PREFIX_DEADLINE).append(task.getDeadline().toString()).append(" ");
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (!tags.isEmpty()) {
                tags.forEach(tag -> sb.append(PREFIX_TAG).append(tag.toString()).append(" "));
            }
        }
        return sb.toString();
    }
}
