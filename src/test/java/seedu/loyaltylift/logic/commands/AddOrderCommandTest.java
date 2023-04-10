package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ADD_ORDER_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ADD_ORDER_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.loyaltylift.model.order.StatusUpdate.DATE_FORMATTER;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.loyaltylift.commons.core.GuiSettings;
import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ReadOnlyAddressBook;
import seedu.loyaltylift.model.ReadOnlyUserPrefs;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.testutil.CustomerBuilder;
import seedu.loyaltylift.testutil.OrderBuilder;

public class AddOrderCommandTest {
    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(INDEX_FIRST, null));
    }

    @Test
    public void constructor_nullCustomerIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, DESC_ADD_ORDER_A));
    }

    @Test
    public void execute_duplicateOrder_failure() throws Exception {
        LocalDate dateToday = LocalDate.now();
        String formattedDate = dateToday.format(DATE_FORMATTER);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();
        Customer customerToAdd = new CustomerBuilder().withName(VALID_NAME_AMY).build();
        Order validOrder = new OrderBuilder().withCustomer(customerToAdd)
                .withCreatedDate(formattedDate).withInitialStatus(formattedDate).build();
        AddOrderCommand.AddOrderDescriptor validAddOrderDescriptor = new AddOrderCommand
                .AddOrderDescriptor(validOrder);
        AddOrderCommand addOrderCommand = new AddOrderCommand(INDEX_FIRST, validAddOrderDescriptor);

        assertCommandFailure(addOrderCommand, modelStub, addOrderCommand.MESSAGE_DUPLICATE_ORDER);
    }

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
        public void setCustomerToDisplay(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Customer getCustomerToDisplay() {
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
        public void setOrderToDisplay(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Order getOrderToDisplay() {
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

    private class ModelStubWithOneOrder extends ModelStub {
        final LocalDate dateToday = LocalDate.now();
        final String formattedDate = dateToday.format(DATE_FORMATTER);
        final Customer customerToAdd = new CustomerBuilder().withName(VALID_NAME_AMY).build();
        final Order orderToAdd = new OrderBuilder().withCustomer(customerToAdd)
                .withCreatedDate(formattedDate).withInitialStatus(formattedDate).build();
        final ArrayList<Customer> customers = new ArrayList<>(
                Arrays.asList(customerToAdd));
        final ArrayList<Order> ordersAdded = new ArrayList<>(
                Arrays.asList(orderToAdd));

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }

        @Override
        public void setOrderToDisplay(Order order) {
        }

        @Override
        public boolean hasOrder(Order order) {
            requireNonNull(order);
            return ordersAdded.stream().anyMatch(order::isSameOrder);
        }

        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            return FXCollections.observableList(customers);
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {}

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
