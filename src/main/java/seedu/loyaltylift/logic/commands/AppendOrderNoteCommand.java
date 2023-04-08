package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.List;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.CreatedDate;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.Status;

/**
 * Overwrites the note of an existing order in the address book.
 */
public class AppendOrderNoteCommand extends Command {

    public static final String COMMAND_WORD = "appendnoteo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends the note of the order identified "
            + "by the index number used in the displayed order list. "
            + "The input will be added to the end of the existing note.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "NOTE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Example note";

    public static final String MESSAGE_APPEND_NOTE_SUCCESS = "Append note for order: \n%1$s";

    private final Index index;
    private final String noteToAppend;

    /**
     * @param index of the order in the filtered order list to edit
     * @param noteToAppend of the customer to be set
     */
    public AppendOrderNoteCommand(Index index, String noteToAppend) {
        requireNonNull(index);
        requireNonNull(noteToAppend);

        this.index = index;
        this.noteToAppend = noteToAppend;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Note newNote = new Note(orderToEdit.getNote().value + this.noteToAppend);
        Order editedOrder = createEditedOrder(orderToEdit, newNote);

        model.setOrder(orderToEdit, editedOrder);
        return new CommandResult(String.format(MESSAGE_APPEND_NOTE_SUCCESS, editedOrder));
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToEdit}
     * with the edited {@code note}.
     */
    private static Order createEditedOrder(Order orderToEdit, Note note) {
        assert orderToEdit != null;

        Customer customer = orderToEdit.getCustomer();
        Name name = orderToEdit.getName();
        Quantity quantity = orderToEdit.getQuantity();
        Address address = orderToEdit.getAddress();
        Status status = orderToEdit.getStatus();
        CreatedDate createdDate = orderToEdit.getCreatedDate();

        return new Order(customer, name, quantity, address, status, createdDate, note);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppendOrderNoteCommand)) {
            return false;
        }

        // state check
        AppendOrderNoteCommand e = (AppendOrderNoteCommand) other;
        return index.equals(e.index)
                && noteToAppend.equals(e.noteToAppend);
    }
}
