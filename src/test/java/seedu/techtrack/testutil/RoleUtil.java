package seedu.techtrack.testutil;

import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.techtrack.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Set;

import seedu.techtrack.logic.commands.AddCommand;
import seedu.techtrack.logic.commands.EditCommand.EditRoleDescriptor;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.util.tag.Tag;




/**
 * A utility class for Role.
 */
public class RoleUtil {

    /**
     * Returns an add command string for adding the {@code role}.
     */
    public static String getAddCommand(Role role) {
        return AddCommand.COMMAND_WORD + " " + getRoleDetails(role);
    }

    /**
     * Returns the part of command string for the given {@code role}'s details.
     */
    public static String getRoleDetails(Role role) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + role.getName().fullName + " ");
        sb.append(PREFIX_CONTACT + role.getContact().value + " ");
        sb.append(PREFIX_EMAIL + role.getEmail().value + " ");
        sb.append(PREFIX_COMPANY + role.getCompany().value + " ");
        sb.append(PREFIX_JOBDESCRIPTION + role.getJobDescription().value + " ");
        sb.append(PREFIX_WEBSITE + role.getWebsite().value + " ");
        sb.append(PREFIX_SALARY + role.getSalary().salary + " ");
        sb.append(PREFIX_DEADLINE + role.getDeadline().deadline + " ");
        sb.append(PREFIX_EXPERIENCE + role.getExperience().value + " ");
        role.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRoleDescriptor}'s details.
     */
    public static String getEditRoleDescriptorDetails(EditRoleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_CONTACT).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getCompany().ifPresent(address -> sb.append(PREFIX_COMPANY).append(address.value).append(" "));
        descriptor.getJobDescription().ifPresent(jobDescription -> sb.append(PREFIX_JOBDESCRIPTION)
                        .append(jobDescription.value).append(" "));
        descriptor.getWebsite().ifPresent(website -> sb.append(PREFIX_WEBSITE).append(website.value).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.salary).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline.deadline).append(" "));
        descriptor.getExperience().ifPresent(experience -> sb.append(PREFIX_EXPERIENCE)
                .append(experience.value).append(" "));
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
