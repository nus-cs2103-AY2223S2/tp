package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ORDER_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ORDER_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.loyaltylift.model.order.StatusUpdate.DATE_FORMATTER;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.loyaltylift.commons.core.GuiSettings;
import seedu.loyaltylift.commons.core.Messages;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.ReadOnlyAddressBook;
import seedu.loyaltylift.model.ReadOnlyUserPrefs;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.EditOrderDescriptorBuilder;
import seedu.loyaltylift.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditOrderCommand.
 */
public class EditOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        LocalDate dateToday = LocalDate.now();
        String formattedDate = dateToday.format(DATE_FORMATTER);
        Order initialOrder = new OrderBuilder().withCreatedDate(formattedDate)
                .withInitialStatus(formattedDate).build();
        Order editedOrder = new OrderBuilder().withName(VALID_NAME_B)
                .withQuantity(VALID_QUANTITY_B).withAddress(VALID_ADDRESS_B)
                .withCreatedDate(formattedDate).withInitialStatus(formattedDate).build();

        model.addOrder(initialOrder);

        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder(editedOrder).build();
        Index lastOrderIndex = Index.fromOneBased(model.getFilteredOrderList().size());
        EditOrderCommand editOrderCommand = new EditOrderCommand(lastOrderIndex, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(initialOrder, editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        LocalDate dateToday = LocalDate.now();
        String formattedDate = dateToday.format(DATE_FORMATTER);
        Order initialOrder = new OrderBuilder().withCreatedDate(formattedDate)
                .withInitialStatus(formattedDate).build();
        Order editedOrder = new OrderBuilder().withAddress(VALID_ADDRESS_B)
                .withCreatedDate(formattedDate).withInitialStatus(formattedDate).build();

        model.addOrder(initialOrder);

        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder(editedOrder).build();
        Index lastOrderIndex = Index.fromOneBased(model.getFilteredOrderList().size());
        EditOrderCommand editOrderCommand = new EditOrderCommand(lastOrderIndex, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(initialOrder, editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Order editedOrder = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());

        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder(editedOrder).build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList).withName(VALID_NAME_B).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST,
                new EditOrderDescriptorBuilder().withName(VALID_NAME_B).build());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder().withName(VALID_NAME_B).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex,
                new EditOrderDescriptorBuilder().withName(VALID_NAME_B).build());

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_FIRST, DESC_ORDER_A);

        // same values -> returns true
        EditOrderCommand.EditOrderDescriptor copyDescriptor =
                new EditOrderCommand.EditOrderDescriptor(DESC_ORDER_A);
        EditOrderCommand commandWithSameValues = new EditOrderCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_SECOND, DESC_ORDER_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_FIRST, DESC_ORDER_B)));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCustomer(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCustomer(Customer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCustomer(Customer target, Customer editedCustomer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCustomerList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredCustomerList(Comparator<Customer> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredOrderList(Comparator<Order> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredCustomerOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCustomerOrderList(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
