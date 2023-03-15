package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingsCommand extends Command {

    public static final String COMMAND_WORD = "editm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meetings list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DATETIME + "TIMEDATE] "
            + "[" + PREFIX_PERSON + "ATTENDEES] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATETIME + "14/02/23"
            + PREFIX_LOCATION + "Stoa Poikile";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingsCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getMeetingsList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor, model);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor, Model model) throws CommandException {
        assert meetingToEdit != null;

        Optional<Set<Name>> updatedAttendees = editMeetingDescriptor.getAttendees();
        Set<Person> updatedPerson = meetingToEdit.getAttendees();
        Title updatedTitle = editMeetingDescriptor.getTitle().orElse(meetingToEdit.getTitle());
        DateTime updatedDateTime = editMeetingDescriptor.getDateTime().orElse(meetingToEdit.getDateTime());
        Location updatedLocation = editMeetingDescriptor.getLocation().orElse(meetingToEdit.getLocation());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        if(updatedAttendees.isPresent()) {
            Set<Name> gotUpdatedAttendees = updatedAttendees.get();
            Set<Person> attendees = new HashSet<>();
            for (Name name : gotUpdatedAttendees) {
                Person person = model.getPersonByName(name);
                if (person == null) {
                    throw new CommandException("Person not found: " + name);
                }
                attendees.add(person);
            }
            return new Meeting(updatedTitle, updatedDateTime, attendees, updatedLocation, updatedDescription);
        }
            return new Meeting(updatedTitle, updatedDateTime, updatedPerson, updatedLocation, updatedDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditMeetingsCommand e = (EditMeetingsCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Set<Name> attendees;
        private Title title;
        private DateTime dateTime;
        private Location location;
        private Description description;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setAttendees(toCopy.attendees);
            setTitle(toCopy.title);
            setDateTime(toCopy.dateTime);
            setLocation(toCopy.location);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(attendees, title, dateTime, location, description);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setAttendees(Set<Name> attendees) {
            this.attendees = attendees;
        }

        public Optional<Set<Name>> getAttendees() {
            return Optional.ofNullable(attendees);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDateTime().equals(e.getDateTime())
                    && getAttendees().equals(e.getAttendees())
                    && getLocation().equals(e.getLocation())
                    && getDescription().equals(e.getDescription());
        }
    }
}
