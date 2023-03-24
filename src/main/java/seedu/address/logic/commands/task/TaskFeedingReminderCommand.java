package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.TaskFeedingReminder;

/**
 * Encapsulates a Task Command for Feeding Reminders.
 * This is not a user command but executed automatically on opening Fish Ahoy
 */
public class TaskFeedingReminderCommand extends TaskCommand {
    public static final String TASK_COMMAND_WORD = "remind";

    public static final String MESSAGE_SUCCESS = "New Feeding Reminder Task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task already exists in Fish Ahoy!";

    private final TaskFeedingReminder toAdd;

    /**
     * Constructs a {@code TaskFeedingReminderCommand} with the {@code Task} to be added to the {@code TaskList}.
     */
    public TaskFeedingReminderCommand(TaskFeedingReminder taskFeedingReminder) {
        requireNonNull(taskFeedingReminder);
        this.toAdd = taskFeedingReminder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd)) {
            //This is a more updated version of the reminder, so delete it
            //We call delete on toAdd as the equals method of Reminders are s.t
            //they are equal
            model.deleteTask(toAdd);
        }
        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
