package seedu.address.logic.commands;

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
            + ": Adds a meeting to the person identified"
            + "by the index number used in the last person listing.\n"
            + "A new meeting will not be added if there are clashes with"
            + "other meetings on the day or period specified.\n"
            + "Parameters: [INDEX] m/ [DATE START] [DATE END]\n"
            + "Example: " + COMMAND_WORD + "1 m/ 30-03-2020 20:10 22:10";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d Meeting: %2$s";
    public static final String MESSAGE_ADD_MEETING_SUCCESS = "Added meeting to Person: %1$s";

    private final Index index;
    private final Meeting meeting;

    /**
     * Adds meeting to the specified {@code Person}
     * @param index index of person
     * @param meeting meeting to add
     */
    public AddMeetingCommand(Index index, Meeting meeting) {
        this.index = index;
        this.meeting = meeting;
    }

    /**
     * Executes meetingAdd command.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object
     * @throws CommandException when index specified is invalid or out of range
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (hasClash(meeting, personToEdit)) {
            String meetingClash = "Meeting specified clashes with other meetings!";
            throw new CommandException(meetingClash);
        }

        personToEdit.getMeetings().add(meeting);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getMeetings());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Checks if any 2 AddMeetingCommand are the same. This is done
     * by checking if both Meeting objects are assigned to the same person
     * and have same day, start and end time.
     * @param other
     * @return
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
        return String.format(MESSAGE_ADD_MEETING_SUCCESS, personToEdit);
    }

    /**
     * Checks if meeting provided clashes with other meetings that the person
     * has
     * @param meetingToCheck Meeting object provided
     * @param personUnderInspection Person the Meeting object is being assigned to
     * @return boolean value
     */
    private boolean hasClash(Meeting meetingToCheck, Person personUnderInspection) {
       ArrayList<Meeting> currentMeetings = personUnderInspection.getMeetings();
       for (Meeting meeting: currentMeetings) {
           if (meetingToCheck.checkTimeClash(meeting)) {
               return true;
           }
       }
       return false;
    }
}
