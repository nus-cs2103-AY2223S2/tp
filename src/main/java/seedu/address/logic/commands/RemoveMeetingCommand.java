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
        + "Parameters: [Index] [Meeting Index]"
        + "Example: " + COMMAND_WORD + " 1 2";
    public static final String MESSAGE_REMOVE_SUCCESS = "Removed meeting from person: %1$s";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Person Index: %1$d, Meeting Index: %1$d";

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

        if (indexMeeting.getZeroBased() >= personToEdit.getMeetings().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        personToEdit.getMeetings().remove(indexMeeting.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
            personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getMeetings());

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the meeting is removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_REMOVE_SUCCESS, personToEdit);
    }
}
