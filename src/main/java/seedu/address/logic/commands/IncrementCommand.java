package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import com.sun.jdi.IncompatibleThreadStateException;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TransactionCount;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;

/**
 * Increments the transaction count of a client without needing to perform manual calculation.
 */
public class IncrementCommand extends Command {

    public static final String COMMAND_WORD = "incr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increments the transaction count by the specified amount.\n"
            + "Parameters: INDEX (must be a postive integer) "
            + PREFIX_TRANSACTION_COUNT + "How many transactions have taken place\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TRANSACTION_COUNT+ "1";

    public static final String MESSAGE_INCREMENT_SUCCESS = "Transaction Count incremented";

    private final EditPersonDescriptor editPersonDescriptor;
    private final Index index;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public IncrementCommand(Index index, EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditCommand.EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        long currentCount = lastShownList.get(index.getZeroBased()).getTransactionCount().getLongValue();
        long incrementCount = editPersonDescriptor.getTransactionCount().get().getLongValue();
        long finalAmount = currentCount + incrementCount;

        editPersonDescriptor.setTransactionCount(new TransactionCount(String.valueOf(finalAmount)));

        Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_INCREMENT_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IncrementCommand)) {
            return false;
        }

        // state check
        IncrementCommand e = (IncrementCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
