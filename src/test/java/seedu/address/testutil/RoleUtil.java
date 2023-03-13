package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBDESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditRoleDescriptor;
import seedu.address.model.job.Role;
import seedu.address.model.tag.Tag;

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
        sb.append(PREFIX_ROLE + role.getName().fullName + " ");
        sb.append(PREFIX_CONTACT + role.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + role.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + role.getAddress().value + " ");
        sb.append(PREFIX_JOBDESCRIPTION + role.getJobDescription().value + " ");
        sb.append(PREFIX_SALARY + role.getSalary().salary + " ");
        sb.append(PREFIX_DEADLINE + role.getDeadline().deadline + " ");
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
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_ROLE).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_CONTACT).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getJobDescription().ifPresent(jobDescription -> sb.append(PREFIX_JOBDESCRIPTION)
                        .append(jobDescription.value).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.salary).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline.deadline).append(" "));
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
