package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.exceptions.CommandException;
import trackr.model.ReadOnlySupplierList;
import trackr.model.SupplierList;
import trackr.model.supplier.Supplier;
import trackr.testutil.SupplierBuilder;
import trackr.testutil.TestUtil.ModelStub;

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
