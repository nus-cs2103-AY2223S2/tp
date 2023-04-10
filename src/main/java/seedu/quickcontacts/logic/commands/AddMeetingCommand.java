package seedu.quickcontacts.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.quickcontacts.model.meeting.DateTime.DATE_FORMAT;
import static seedu.quickcontacts.model.meeting.DateTime.TIME_FORMAT;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Description;
import seedu.quickcontacts.model.meeting.Location;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.meeting.Title;
import seedu.quickcontacts.model.person.Name;
import seedu.quickcontacts.model.person.Person;

/**
 * Adds a meeting to the address book.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the address book. "
        + "Required Parameters: "
        + PREFIX_MEETING_TITLE + "MEETING_TITLE "
        + PREFIX_DATETIME + DATE_FORMAT + " " + TIME_FORMAT + "\n"
        + "Optional Parameters: "
        + PREFIX_PERSON + "NAME "
        + PREFIX_LOCATION + "LOCATION "
        + PREFIX_DESCRIPTION + "DESCRIPTION\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_MEETING_TITLE + "Meeting with John "
        + PREFIX_PERSON + "John Doe "
        + PREFIX_DATETIME + "02/02/2022 12:00 "
        + PREFIX_LOCATION + "Zoom "
        + PREFIX_DESCRIPTION + "Discuss about the project";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book";
    public static final String COMMAND_DESCRIPTION = "Adds a meeting to the address book.";

    private final Title meetingTitle;
    private final DateTime dateTime;
    private final Set<Name> attendeeNames = new HashSet<>();
    private final Location location;
    private final Description description;

    /**
     * A more straightforward way to create an AddMeetingCommand to add a {@code Meeting} class.
     * Used primarily for testing.
     *
     * @param meeting to be added
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        this.meetingTitle = meeting.getTitle();
        this.dateTime = meeting.getDateTime();
        this.attendeeNames.addAll(meeting.getAttendees().stream().map(Person::getName).collect(Collectors.toSet()));
        this.location = meeting.getLocation();
        this.description = meeting.getDescription();
    }

    /**
     * Creates an AddMeetingCommand to add a {@code Meeting} class with the specified attributes {@code Title},
     * {@code attendees}, {@code DateTime}, {@code Location}, {@code Description}
     */
    public AddMeetingCommand(Title meetingTitle, DateTime dateTime, Set<Name> attendees, Location location,
                             Description description) {
        requireAllNonNull(meetingTitle, attendees, dateTime);
        this.meetingTitle = meetingTitle;
        this.dateTime = dateTime;
        this.attendeeNames.addAll(attendees);
        this.location = location;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        final Set<Person> attendees = new HashSet<>();
        for (Name name : attendeeNames) {
            Person person = model.getPersonByName(name);
            if (person == null) {
                throw new CommandException("Person not found: " + name);
            }
            attendees.add(person);
        }

        Meeting meetingToAdd = new Meeting(meetingTitle, dateTime, attendees, location, description);

        if (model.hasMeeting(meetingToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }
        model.addMeeting(meetingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddMeetingCommand // instanceof handles nulls
            && meetingTitle.equals(((AddMeetingCommand) other).meetingTitle)
            && dateTime.equals(((AddMeetingCommand) other).dateTime)
            && attendeeNames.equals(((AddMeetingCommand) other).attendeeNames)
            && location.equals(((AddMeetingCommand) other).location)
            && description.equals(((AddMeetingCommand) other).description));
    }
}
