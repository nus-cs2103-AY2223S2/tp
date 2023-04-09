package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ADD_ORDER_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ADD_ORDER_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.model.order.StatusUpdate.DATE_FORMATTER;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.loyaltylift.commons.core.GuiSettings;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.ReadOnlyAddressBook;
import seedu.loyaltylift.model.ReadOnlyUserPrefs;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.AddOrderDescriptorBuilder;
import seedu.loyaltylift.testutil.CustomerBuilder;
import seedu.loyaltylift.testutil.OrderBuilder;

public class AddOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(INDEX_FIRST, null));
    }

    @Test
    public void constructor_nullCustomerIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, DESC_ADD_ORDER_A));
    }
/*
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws Exception {
        LocalDate dateToday = LocalDate.now();
        String formattedDate = dateToday.format(DATE_FORMATTER);
        Order orderToAdd =
        Order addedOrder = new OrderBuilder(orderToAdd).withNextStatus(formattedDate).build();

        Order validOrder = new OrderBuilder().build();
        AddOrderCommand.AddOrderDescriptor validAddOrderDescriptor = new AddOrderDescriptorBuilder(validOrder).build();
        AddOrderCommand validAddOrderCommand = new AddOrderCommand(INDEX_FIRST, validAddOrderDescriptor);

        String expectedMessage = String.format(AddOrderCommand.MESSAGE_SUCCESS, validAddOrderCommand);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        CommandResult commandResult = new AddOrderCommand(INDEX_FIRST, addOrderCommandDescriptor).execute(modelStub);

        assertCommandSuccess(addOrderCommand, model, expectedMessage, expectedModel);

        assertEquals(
                String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.orderAdded);
    }

    @Test
    public void execute_duplicateOrder_addSuccessful() {
        Order validOrder = new OrderBuilder().build();
        AddOrderCommand.AddOrderDescriptor validOrderDescriptor = new AddOrderDescriptorBuilder(validOrder).build();
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST, validOrderDescriptor);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addOrder();
        assertThrows(CommandException.class,
                AddOrderCommand.MESSAGE_DUPLICATE_ORDER, () -> addOrderCommand.execute(modelStub));
    }
    */

    @Test
    public void equals() {
        AddOrderCommand addOrderACommand = new AddOrderCommand(INDEX_FIRST, DESC_ADD_ORDER_A);
        AddOrderCommand addOrderBCommand = new AddOrderCommand(INDEX_SECOND, DESC_ADD_ORDER_B);

        // same object -> returns true
        assertTrue(addOrderACommand.equals(addOrderACommand));

        // same values -> returns true
        AddOrderCommand addOrderACommandCopy = new AddOrderCommand(INDEX_FIRST, DESC_ADD_ORDER_A);
        assertTrue(addOrderACommand.equals(addOrderACommandCopy));

        // different types -> returns false
        assertFalse(addOrderACommand.equals(1));

        // null -> returns false
        assertFalse(addOrderACommand.equals(null));

        // different customer -> returns false
        assertFalse(addOrderACommand.equals(addOrderBCommand));
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

    /**
     * A Model stub that contains a single customer.
     */
    private class ModelStubWithOrder extends ModelStub {
        private final Order order;

        ModelStubWithOrder(Order order) {
            requireNonNull(order);
            this.order = order;
        }

        @Override
        public boolean hasOrder(Order order) {
            requireNonNull(order);
            return this.order.isSameOrder(order);
        }
    }

}
