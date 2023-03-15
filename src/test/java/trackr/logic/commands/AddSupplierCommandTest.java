package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.ReadOnlyUserPrefs;
import trackr.model.SupplierList;
import trackr.model.order.Order;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;
import trackr.testutil.SupplierBuilder;

public class AddSupplierCommandTest {

    @Test
    public void constructor_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSupplierCommand(null));
    }

    @Test
    public void execute_supplierAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingSupplierAdded modelStub = new ModelStubAcceptingSupplierAdded();
        Supplier validSupplier = new SupplierBuilder().build();

        CommandResult commandResult = new AddSupplierCommand(validSupplier).execute(modelStub);

        assertEquals(String.format(AddSupplierCommand.MESSAGE_SUCCESS, validSupplier),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSupplier), modelStub.suppliersAdded);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        Supplier validSupplier = new SupplierBuilder().build();
        AddSupplierCommand addSupplierCommand = new AddSupplierCommand(validSupplier);
        ModelStub modelStub = new ModelStubWithSupplier(validSupplier);

        assertThrows(CommandException.class,
                AddSupplierCommand.MESSAGE_DUPLICATE_SUPPLIER, () -> addSupplierCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Supplier alice = new SupplierBuilder().withName("Alice").build();
        Supplier bob = new SupplierBuilder().withName("Bob").build();
        AddSupplierCommand addAliceCommand = new AddSupplierCommand(alice);
        AddSupplierCommand addBobCommand = new AddSupplierCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddSupplierCommand addAliceCommandCopy = new AddSupplierCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different Supplier -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getTrackrFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackrFilePath(Path trackrFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSupplierList(ReadOnlySupplierList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySupplierList getSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSupplier(Supplier target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSupplier(Supplier target, Supplier editedSupplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Supplier> getFilteredSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskList(ReadOnlyTaskList taskList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrderList(ReadOnlyOrderList orderList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyOrderList getOrderList() {
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
        public void addOrder(Order order) {
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
    }

    /**
     * A Model stub that contains a single Supplier.
     */
    private class ModelStubWithSupplier extends ModelStub {
        private final Supplier supplier;

        ModelStubWithSupplier(Supplier supplier) {
            requireNonNull(supplier);
            this.supplier = supplier;
        }

        @Override
        public boolean hasSupplier(Supplier supplier) {
            requireNonNull(supplier);
            return this.supplier.isSameSupplier(supplier);
        }
    }

    /**
     * A Model stub that always accept the Supplier being added.
     */
    private class ModelStubAcceptingSupplierAdded extends ModelStub {
        final ArrayList<Supplier> suppliersAdded = new ArrayList<>();

        @Override
        public boolean hasSupplier(Supplier supplier) {
            requireNonNull(supplier);
            return suppliersAdded.stream().anyMatch(supplier::isSameSupplier);
        }

        @Override
        public void addSupplier(Supplier supplier) {
            requireNonNull(supplier);
            suppliersAdded.add(supplier);
        }

        @Override
        public ReadOnlySupplierList getSupplierList() {
            return new SupplierList();
        }
    }

}
