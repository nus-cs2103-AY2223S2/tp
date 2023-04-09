package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;

import bookopedia.commons.core.index.Index;
import bookopedia.logic.commands.exceptions.CommandException;
import bookopedia.model.Model;
import bookopedia.model.person.Person;

/**
 * Deletes a delivery identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the delivery identified by the index number used in the displayed delivery list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DELIVERY_SUCCESS = "Deleted Delivery: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DELIVERY_SUCCESS, personToDelete),
                true, null, -1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
