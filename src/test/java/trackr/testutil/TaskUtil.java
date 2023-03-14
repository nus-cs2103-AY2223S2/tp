package trackr.testutil;

import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.logic.commands.AddTaskCommand;
import trackr.model.task.Task;
import trackr.model.task.TaskDescriptor;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an AddTaskCommand string for adding the {@code task}.
     */
    public static String getAddTaskCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns an AddTaskCommand string with COMMAND_WORD_SHORTCUT for adding the {@code task}.
     */
    public static String getAddTaskCommandShortcut(Task task) {
        return AddTaskCommand.COMMAND_WORD_SHORTCUT + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getTaskName().fullTaskName + " ");
        sb.append(PREFIX_DEADLINE + task.getTaskDeadline().toJsonString() + " ");
        sb.append(PREFIX_STATUS + task.getTaskStatus().toJsonString() + " ");
        return sb.toString();
    }

    // Returns the part of command string for the given {@code TaskDescriptor}'s details.
    public static String getTaskDescriptorDetails(TaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTaskName()
                .ifPresent(taskName -> sb.append(PREFIX_NAME).append(taskName.fullTaskName).append(" "));
        descriptor.getTaskDeadline()
                .ifPresent(taskDeadline -> sb.append(PREFIX_DEADLINE).append(taskDeadline.toJsonString()).append(" "));
        descriptor.getTaskStatus()
                .ifPresent(taskStatus -> sb.append(PREFIX_STATUS).append(taskStatus.toJsonString()).append(" "));
        return sb.toString();
    }
}
