package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.List;
import java.util.Set;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Marked;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.model.tag.Tag;

/**
 * Overwrites the note of an existing customer in the address book.
 */
public class AppendCustomerNoteCommand extends Command {

    public static final String COMMAND_WORD = "appendnotec";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends the note of the customer identified "
            + "by the index number used in the displayed customer list. "
            + "The input will be added to the end of the existing note.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "NOTE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Example note";

    public static final String MESSAGE_APPEND_NOTE_SUCCESS = "Appended note for customer: \n%1$s";

    private final Index index;
    private final String noteToAppend;

    /**
     * @param index of the customer in the filtered customer list to edit
     * @param noteToAppend of the customer to be set
     */
    public AppendCustomerNoteCommand(Index index, String noteToAppend) {
        requireNonNull(index);
        requireNonNull(noteToAppend);

        this.index = index;
        this.noteToAppend = noteToAppend;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Note newNote = new Note(customerToEdit.getNote().value + this.noteToAppend);
        Customer editedCustomer = createEditedCustomer(customerToEdit, newNote);

        model.setCustomer(customerToEdit, editedCustomer);
        return new CommandResult(String.format(MESSAGE_APPEND_NOTE_SUCCESS, editedCustomer));
    }

    /**
     * Creates and returns a {@code Customer} with the details of {@code customerToEdit}
     * with the edited {@code note}.
     */
    private static Customer createEditedCustomer(Customer customerToEdit, Note note) {
        assert customerToEdit != null;

        Name name = customerToEdit.getName();
        Phone phone = customerToEdit.getPhone();
        Email email = customerToEdit.getEmail();
        Address address = customerToEdit.getAddress();
        Set<Tag> tags = customerToEdit.getTags();
        CustomerType customerType = customerToEdit.getCustomerType();
        Points points = customerToEdit.getPoints();
        Marked marked = customerToEdit.getMarked();

        return new Customer(customerType, name, phone, email, address, tags, points, marked, note);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppendCustomerNoteCommand)) {
            return false;
        }

        // state check
        AppendCustomerNoteCommand e = (AppendCustomerNoteCommand) other;
        return index.equals(e.index)
                && noteToAppend.equals(e.noteToAppend);
    }
}
