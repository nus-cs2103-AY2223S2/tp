package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Adds a reminder to the address book.
 */
public class AddReminderCommand extends Command {
    public static final CommandGroup COMMAND_GROUP = CommandGroup.REMINDER;

    public static final String COMMAND_WORD = "add_reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reminder.\n"
            + "Parameters: ["
            + PREFIX_DESCRIPTION + "DESCRIPTION(Max 50 characters, including space!)] "
            + PREFIX_TIME + "YYYY-MM-DD HH:MM"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "go home "
            + PREFIX_TIME + "2023-03-03 17:00";

    public static final String MESSAGE_SUCCESS = "New reminder added";

    public static final String MESSAGE_DATE_TIME_CONSTRAINT = "Dates must be valid date "
            + "- only contain numeric characters and spaces, "
            + "and it should not be blank.\n"
            + "Date must have format like this: YYYY-mm-DD"
            + "\nTime must be a valid timing - only contains numeric characters and colon."
            + "\nTime must be of format HH:mm";

    private final Reminder toAdd;

    /**
     * Creates an AddReminderCommand to add the specified {@code Reminder}
     */
    public AddReminderCommand(Reminder reminder) {
        requireNonNull(reminder);
        toAdd = reminder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addReminder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReminderCommand // instanceof handles nulls
                && toAdd.equals(((AddReminderCommand) other).toAdd));
    }
}
