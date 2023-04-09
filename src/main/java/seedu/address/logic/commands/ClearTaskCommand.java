package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TaskList;

/**
 * Clears the tasks of an existing person in the address book.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleartask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the tasks of the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_CLEAR_TASK_SUCCESS = "Cleared tasklist of Person: %1$s";
    public static final String MESSAGE_CLEAR_TASK_FAILURE = "Failure to clear tasklist of Person: %1$s";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to clear the tasks
     */
    public ClearTaskCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getGender(),
                personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getCompany(), personToEdit.getLocation(),
                personToEdit.getOccupation(), personToEdit.getJobTitle(),
                personToEdit.getAddress(), personToEdit.getRemark(),
                personToEdit.getTags(), personToEdit.getTasks().clear(), personToEdit.getStatus());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the tasks are cleared from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = personToEdit.getTasks().isEmpty() ? MESSAGE_CLEAR_TASK_SUCCESS : MESSAGE_CLEAR_TASK_FAILURE;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearTaskCommand)) {
            return false;
        }

        assert other instanceof ClearTaskCommand;
        // state check
        ClearTaskCommand e = (ClearTaskCommand) other;
        return index.equals(e.index);
    }
}
