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
public class SetOrderNoteCommand extends Command {

    public static final String COMMAND_WORD = "setnoteo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Overwrites the note of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "NOTE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Example note";

    public static final String MESSAGE_SET_NOTE_SUCCESS = "Set note for order: \n%1$s";

    private final Index index;
    private final Note note;

    /**
     * @param index of the order in the filtered order list to edit
     * @param note of the order to be set
     */
    public SetOrderNoteCommand(Index index, Note note) {
        requireNonNull(index);
        requireNonNull(note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Order editedOrder = createEditedOrder(orderToEdit, this.note);

        model.setOrder(orderToEdit, editedOrder);
        return new CommandResult(String.format(MESSAGE_SET_NOTE_SUCCESS, editedOrder));
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
        if (!(other instanceof SetOrderNoteCommand)) {
            return false;
        }

        // state check
        SetOrderNoteCommand e = (SetOrderNoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
