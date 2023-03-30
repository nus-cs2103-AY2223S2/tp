package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all reminders as Notifications to the user.
 */
public class ListReminderCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.REMINDER;

    public static final String COMMAND_WORD = "list_reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all reminders.\n";
    public static final String SHOWING_REMINDERLIST_MESSAGE = "Opened reminder list window.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(SHOWING_REMINDERLIST_MESSAGE,
                false, false, true, false, false);
    }
}
