package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * Adds meeting to a specified person
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "meetingAdd";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a meeting to the person identified "
        + "by the index number used in the last person listing.\n"
        + "A new meeting will not be added if there are clashes with"
        + "other meetings on the day or period specified.\n"
        + "Parameters: [INDEX] md/ [DESC] ms/ [DATE&TIME START] me/ [DATE&TIME END]\n"
        + "INDEX is a positive number\n"
        + "Example: " + COMMAND_WORD + " 1 md/ Test ms/ 30-03-2020 20:10 me/ 30-03-2020 22:10";
    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added meeting to Person: %1$s";

    private final Index index;
    private final Meeting meeting;

    /**
     * Adds meeting to the specified {@code Person}
     *
     * @param index   index of person
     * @param meeting meeting to add
     */
    public AddMeetingCommand(Index index, Meeting meeting) {
        requireNonNull(index);
        requireNonNull(meeting);
        this.index = index;
        this.meeting = meeting;
    }

    /**
     * Executes meetingAdd command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object
     * @throws CommandException when index specified is invalid or out of range
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (meeting.isCorrectPeriod()) {
            String incorrectDateTimeMsg = "Start date and time should be before end date and time!";
            throw new CommandException(incorrectDateTimeMsg);
        }

        if (meeting.isPastDateTime()) {
            String pastTodayMsg = "Date and time given is before today's date and time!";
            throw new CommandException(pastTodayMsg);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (hasClash(meeting, personToEdit)) {
            String meetingClashMsg = "Meeting specified clashes with other meetings!";
            throw new CommandException(meetingClashMsg);
        }

        // Adds meeting and returns edited person
        Person editedPerson = model.addMeeting(personToEdit, meeting);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Checks if any 2 AddMeetingCommand are the same. This is done
     * by checking if both Meeting objects are assigned to the same person
     * and have same day, start and end time.
     *
     * @param other Other AddMeetingCommand object to compare to
     * @return boolean value on whether both objects are the same or not
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        // state check
        AddMeetingCommand e = (AddMeetingCommand) other;
        return index.equals(e.index)
            && meeting.equals(e.meeting);
    }

    /**
     * Generates a command execution success message based on whether
     * the meeting is added to
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        requireNonNull(personToEdit);
        return String.format(MESSAGE_ADD_MEETING_SUCCESS, personToEdit.getName());
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
        for (Meeting meeting : currentMeetings) {
            if (meetingToCheck.checkTimeClash(meeting)) {
                return true;
            }
        }
        return false;
    }

}
