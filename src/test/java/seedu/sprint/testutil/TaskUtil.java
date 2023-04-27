package seedu.sprint.testutil;

import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.sprint.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.sprint.logic.commands.EditTaskCommand.EditTaskDescriptor;

/**
 * A utility class for Task.
 */
public class TaskUtil {
    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE)
                .append(deadline).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description).append(" "));
        return sb.toString();
    }
}


