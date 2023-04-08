package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
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
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCustomerCommand}.
 */
public class MarkCustomerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToMark = model.getFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        CustomerType customerType = customerToMark.getCustomerType();
        Name name = customerToMark.getName();
        Phone phone = customerToMark.getPhone();
        Email email = customerToMark.getEmail();
        Address address = customerToMark.getAddress();
        Set<Tag> tags = customerToMark.getTags();
        Points points = customerToMark.getPoints();
        Note note = customerToMark.getNote();
        Customer markedCustomer = new Customer(customerType, name, phone, email, address, tags, points,
                new Marked(true), note);
        MarkCustomerCommand markCustomerCommand = new MarkCustomerCommand(INDEX_FIRST);

        String expectedMessage = String.format(MarkCustomerCommand.MESSAGE_MARK_CUSTOMER_SUCCESS, markedCustomer);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), markedCustomer);

        assertCommandSuccess(markCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        MarkCustomerCommand markCustomerCommand = new MarkCustomerCommand(outOfBoundIndex);

        assertCommandFailure(markCustomerCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MarkCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.sortFilteredCustomerList(Customer.SORT_POINTS);

        Customer customerToMark = model.getFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        CustomerType customerType = customerToMark.getCustomerType();
        Name name = customerToMark.getName();
        Phone phone = customerToMark.getPhone();
        Email email = customerToMark.getEmail();
        Address address = customerToMark.getAddress();
        Set<Tag> tags = customerToMark.getTags();
        Points points = customerToMark.getPoints();
        Note note = customerToMark.getNote();
        Customer markedCustomer = new Customer(customerType, name, phone, email, address, tags, points,
                new Marked(true), note);
        MarkCustomerCommand markCustomerCommand = new MarkCustomerCommand(INDEX_FIRST);

        String expectedMessage = String.format(MarkCustomerCommand.MESSAGE_MARK_CUSTOMER_SUCCESS, markedCustomer);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), markedCustomer);

        assertCommandSuccess(markCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        MarkCustomerCommand markCustomerCommand = new MarkCustomerCommand(outOfBoundIndex);

        assertCommandFailure(markCustomerCommand, model,
                String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MarkCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        MarkCustomerCommand markFirstCommand = new MarkCustomerCommand(INDEX_FIRST);
        MarkCustomerCommand markSecondCommand = new MarkCustomerCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCustomerCommand markFirstCommandCopy = new MarkCustomerCommand(INDEX_FIRST);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}
