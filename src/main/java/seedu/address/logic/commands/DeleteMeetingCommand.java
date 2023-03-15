package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Deletes a meeting from the address book
 */
public class DeleteMeetingCommand extends Command {
    public static final String COMMAND_WORD = "delm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a meeting from the address book.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Meeting Deleted: %1$s";

    private final Index index;

    /**
     * Constructs a DeleteMeetingCommand
     * @param index The one index of the meeting in the address book
     */
    public DeleteMeetingCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getMeetingsList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meetingToDelete = lastShownList.get(index.getZeroBased());
        System.out.println("Reached here 1");
        model.removeMeeting(meetingToDelete);
        System.out.println("Reached here 2");
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMeetingCommand // instanceof handles nulls
                && index.equals(((DeleteMeetingCommand) other).index)); // state check
    }
}
