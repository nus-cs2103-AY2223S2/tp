package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * Updates an existing Meeting's start or end, or both
 */
public class UpdateMeetingCommand extends Command {
    public static final String COMMAND_WORD = "meetingUpdate";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates a meeting of a person identified "
        + "by the index number of the person and index number of the person's meeting.\n"
        + "The meeting will not be updated if there are clashes with"
        + "other meetings on the day or period specified, " + "or if the end is before the start.\n"
        + "Parameters: [INDEX] [MEETINGINDEX] md/[DESCRIPTION] ms/[DATE START] me/[DATE END]\n"
        + "INDEX is a positive number\n"
        + "Example: " + COMMAND_WORD + " 1 2 md/Policy discussion ms/30-03-2020 20:10 me/22:10";
    public static final String MESSAGE_UPDATE_MEETING_SUCCESS = "Meeting of Person updated: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final Index meetingIndex;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * Constructor for UpdateMeetingCommand object
     *
     * @param index                 index of Person
     * @param meetingIndex          Index of meeting to be updated
     * @param editMeetingDescriptor Object storing edited meeting
     */
    public UpdateMeetingCommand(Index index, Index meetingIndex, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(meetingIndex);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.meetingIndex = meetingIndex;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor)
            throws CommandException {
        assert meetingToEdit != null;

        String updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        LocalDateTime updatedStart = editMeetingDescriptor.getStart().orElse(meetingToEdit.getStart());
        LocalDateTime updatedEnd = editMeetingDescriptor.getEnd().orElse(meetingToEdit.getEnd());
        Meeting m = new Meeting(updatedDescription, updatedStart, updatedEnd);
        if (m.isCorrectPeriod()) {
            String incorrectDateTimeMsg = "Start date and time should be before end date and time!";
            throw new CommandException(incorrectDateTimeMsg);
        }
        return m;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (meetingIndex.getZeroBased() >= personToEdit.getMeetings().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meetingToEdit = personToEdit.getMeetings().get(meetingIndex.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);
        if (editedMeeting.isCorrectPeriod()) {
            String incorrectDateTimeMsg = "Start date and time should be before end date and time!";
            throw new CommandException(incorrectDateTimeMsg);
        }
        if (hasClash(editedMeeting, personToEdit)) {
            String meetingClashMsg = "Meeting specified clashes with other meetings!";
            throw new CommandException(meetingClashMsg);
        }
        model.updateMeeting(personToEdit, meetingIndex, editedMeeting);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_UPDATE_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Checks if meeting provided clashes with other meetings that the person
     * has
     *
     * @param meetingToCheck        Meeting object provided
     * @param personUnderInspection Person the Meeting object is being assigned to
     * @return boolean value
     */
    private boolean hasClash(Meeting meetingToCheck, Person personUnderInspection) {
        requireNonNull(personUnderInspection);
        requireNonNull(meetingToCheck);
        ArrayList<Meeting> currentMeetings = personUnderInspection.getMeetings();
        for (int i = 0; i < currentMeetings.size(); i++) {
            if (i == meetingIndex.getZeroBased()) {
                continue;
            }
            Meeting meeting = currentMeetings.get(i);
            if (meetingToCheck.checkTimeClash(meeting)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.index + " " + this.meetingIndex + " " + this.editMeetingDescriptor;
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateMeetingCommand)) {
            return false;
        }

        // state check
        UpdateMeetingCommand e = (UpdateMeetingCommand) other;
        return index.equals(e.index) && meetingIndex.equals(e.meetingIndex)
            && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to update the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private String description;
        private LocalDateTime start;
        private LocalDateTime end;

        public EditMeetingDescriptor() {
        }

        /**
         * Constructor for EditMeetingDescriptor object
         *
         * @param toCopy Meeting to be edited
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setDescription(toCopy.description);
            setStart(toCopy.start);
            setEnd(toCopy.end);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, start, end);
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<LocalDateTime> getStart() {
            return Optional.ofNullable(start);
        }

        public void setStart(LocalDateTime start) {
            this.start = start;
        }

        public Optional<LocalDateTime> getEnd() {
            return Optional.ofNullable(end);
        }

        public void setEnd(LocalDateTime end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return this.description + " " + this.start + " " + this.end;
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

            return getDescription().equals(e.getDescription()) && getStart().equals(e.getStart())
                && getEnd().equals(e.getEnd());
        }
    }
}

