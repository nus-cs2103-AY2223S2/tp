package seedu.quickcontacts.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.meeting.Meeting;

/**
 * Lists all meetings in the address book to the user.
 */
public class ViewMeetingsCommand extends Command {
    public static final String COMMAND_WORD = "listm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all meetings in the address book.\n"
            + "Example: " + COMMAND_WORD;
    public static final String COMMAND_DESCRIPTION = "Lists all meetings in the address book.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder result = new StringBuilder();
        for (Meeting m : model.getMeetingsList()) {
            result.append(m.getTitle());
            result.append("\n");
            result.append("Description: ").append(m.getDescription());
            result.append("\n");
            result.append("At: ").append(m.getDateTime());
            result.append("\n");
            result.append("Attendees: ").append(m.getAttendees());
            result.append("\n");
            result.append("Location: ").append(m.getLocation());
            result.append("\n\n");
        }
        return new CommandResult(result.toString());
    }
}
