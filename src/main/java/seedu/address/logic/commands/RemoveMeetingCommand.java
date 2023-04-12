package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Removes a meeting by specified index from specified person
 */
public class RemoveMeetingCommand extends Command {
    public static final String COMMAND_WORD = "meetingRemove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Removes a meeting by specified index number from the person identified"
        + "by the index number used in the last person listing.\n"
        + "If there no meeting to be removed, nothing will be removed.\n"
        + "Parameters: [CLIENT_INDEX] [MEETING_INDEX]\n"
        + "CLIENT_INDEX AND MEETING_INDEX are both positive numbers\n"
        + "Example: " + COMMAND_WORD + " 1 2";
    public static final String MESSAGE_REMOVE_SUCCESS = "Removed meeting from person: %1$s";

    private Index indexPerson;
    private Index indexMeeting;

    /**
     * Removes meeting at specified index from specified {@code Person}
     *
     * @param indexPerson  index of Person to look up
     * @param indexMeeting index of meeting in Person to remove
     */
    public RemoveMeetingCommand(Index indexPerson, Index indexMeeting) {
        this.indexPerson = indexPerson;
        this.indexMeeting = indexMeeting;
    }

    /**
     * Executes meetingRemove command
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object
     * @throws CommandException when index of person or meeting specified is out of range or invalid
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexPerson.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(indexPerson.getZeroBased());

        if (indexMeeting.getZeroBased() > personToEdit.getMeetings().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        //Removes meeting and returns edited Person
        Person editedPerson = model.removeMeeting(personToEdit, indexMeeting);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the meeting is removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_REMOVE_SUCCESS, personToEdit.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(o instanceof RemoveMeetingCommand)) {
            return false;
        }

        // state check
        RemoveMeetingCommand e = (RemoveMeetingCommand) o;
        return this.indexPerson.equals(e.indexPerson)
            && this.indexMeeting.equals(e.indexMeeting);
    }
}
