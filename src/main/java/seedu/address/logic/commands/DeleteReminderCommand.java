package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Deletes a reminder using it's displayed index from notifications.
 */
public class DeleteReminderCommand extends Command {
    public static final String COMMAND_WORD = "delete_reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the reminder identified by the index number used in the displayed notification.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Deleted Reminder";

    private final int targetIndex;

    public DeleteReminderCommand(int targetIndex) {
        this.targetIndex = targetIndex - 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reminder> reminderList = model.getReminderList();

        if (targetIndex > reminderList.size() - 1 || targetIndex < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }
        model.deleteReminder(targetIndex);
        return new CommandResult(String.format(MESSAGE_DELETE_REMINDER_SUCCESS));
    }
}
