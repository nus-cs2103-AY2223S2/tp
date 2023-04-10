package seedu.quickcontacts.testutil;

import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.quickcontacts.logic.commands.AddMeetingCommand;
import seedu.quickcontacts.logic.commands.EditMeetingsCommand.EditMeetingDescriptor;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Name;

/**
 * A utility class for Meeting.
 */
public class MeetingUtil {

    /**
     * Returns an AddMeeting command string for adding the {@code meeting}.
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
        meeting.getAttendees().forEach(
                s -> sb.append(PREFIX_PERSON).append(s.getName().fullName).append(" ")
        );
        sb.append(PREFIX_DATETIME).append(meeting.getDateTime().getDateTime()).append(" ");
        sb.append(PREFIX_LOCATION).append(meeting.getLocation().toString()).append(" ");
        sb.append(PREFIX_DESCRIPTION).append(meeting.getDescription().toString()).append(" ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMeetingDescriptor}'s details.
     */
    public static String getEditMeetingDescriptorDetails(EditMeetingDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_MEETING_TITLE).append(title).append(" "));
        descriptor.getDateTime().ifPresent(
                dateTime -> sb.append(PREFIX_DATETIME).append(dateTime.getDateTime()).append(" "));
        descriptor.getLocation().ifPresent(
                location -> sb.append(PREFIX_LOCATION).append(location).append(" "));
        descriptor.getDescription().ifPresent(
                description -> sb.append(PREFIX_DESCRIPTION).append(description).append(" "));
        if (descriptor.getAttendees().isPresent()) {
            Set<Name> attendees = descriptor.getAttendees().get();
            if (attendees.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                attendees.forEach(s -> sb.append(PREFIX_PERSON).append(s.fullName).append(" "));
            }
        }
        return sb.toString();
    }
}
