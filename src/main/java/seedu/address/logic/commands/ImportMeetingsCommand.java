package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;

/**
 * This class represents a command for importing of meetings
 */
public class ImportMeetingsCommand extends Command {
    public static final String COMMAND_WORD = "importm";
    static final String DUPLICATE_MEETING = "Duplicate meeting found";
    static final String SUCCESS = "Meetings imported";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports meetings in JSON format\n" + "Parameters: f/ "
            + "true: to force imports regardless of duplicates";
    private final List<Meeting> meetings;
    private final boolean isForced;

    /**
     * Creates a new ImportMeetingsCommand
     *
     * @param meetings meetings to be imported
     * @param isForced whether to force imports regardless of duplicates
     */
    public ImportMeetingsCommand(List<Meeting> meetings, boolean isForced) {
        this.isForced = isForced;
        this.meetings = meetings;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            if (isForced) {
                for (Meeting meeting : meetings) {
                    if (!model.hasMeeting(meeting)) {
                        model.addMeeting(meeting);
                    }
                }
            } else {
                for (Meeting meeting : meetings) {
                    if (model.hasMeeting(meeting)) {
                        throw new DuplicateMeetingException();
                    }
                }
                for (Meeting p : meetings) {
                    model.addMeeting(p);
                }
            }
            return new CommandResult(SUCCESS);
        } catch (DuplicateMeetingException e) {
            throw new CommandException(DUPLICATE_MEETING);
        }
    }
    @Override
    public boolean equals(Object o) {
        return o == this // short circuit if same object
                || (o instanceof ImportMeetingsCommand
                && meetings.equals(((ImportMeetingsCommand) o).meetings));
    }

}
