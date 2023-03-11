package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

public class RemoveMeetingCommand extends Command{
    public static final String COMMAND_WORD = "meetingRemove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a meeting from the person identified"
            + "by the index number used in the last person listing.\n"
            + "If there no meeting to be removed, nothing will be removed.\n"
            + "Parameters: [Index]"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_REMOVE_SUCCESS = "Removed meeting from person: %1$s";

    private Index index;

    public RemoveMeetingCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        personToEdit.getMeetings().remove(0);
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
