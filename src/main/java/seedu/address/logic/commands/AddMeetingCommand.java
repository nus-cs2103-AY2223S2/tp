package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.*;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a meeting to the address book.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the address book. "
            + "Parameters: "
            + PREFIX_MEETING_TITLE + "MEETING "
            + PREFIX_PERSON + "NAME "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEETING_TITLE + "Meeting with John "
            + PREFIX_PERSON + "John Doe " + PREFIX_DATE + "2020-10-10";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book";

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
     * Creates an AddMeetingCommand to add a {@code Meeting} class with the specified attributes {@code Title}, {@code attendees},
     * {@code DateTime}, {@code Location}, {@code Description}
     */
    public AddMeetingCommand(Title meetingTitle, DateTime dateTime, Set<Name> attendees, Location location,
                             Description description) {
        requireAllNonNull(meetingTitle, attendees, dateTime, location, description);
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
