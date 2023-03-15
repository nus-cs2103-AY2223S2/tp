package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class for Meeting.
 */
public class MeetingUtil {
    /**
     * Returns an add command string for adding the {@code meeting}.
     */
    public static String getAddMeetingCommand(Meeting meeting) {
        return AddMeetingCommand.COMMAND_WORD + " " + getMeetingDetails(meeting);
    }

    /**
     * Returns the part of command string for the given {@code meeting}'s details.
     */
    public static String getMeetingDetails(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MEETING_TITLE).append(meeting.getTitle().toString()).append(" ");
        meeting.getAttendees().forEach(p -> sb.append(PREFIX_PERSON).append(p.getName()).append(" "));
        sb.append(PREFIX_DATETIME).append(meeting.getDateTime().toString()).append(" ");
        sb.append(PREFIX_LOCATION).append(meeting.getLocation().toString()).append(" ");
        sb.append(PREFIX_DESCRIPTION).append(meeting.getDescription().toString()).append(" ");
        System.out.println(sb);
        return sb.toString();
    }
}
