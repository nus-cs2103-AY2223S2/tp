package trackr.logic.commands.supplier;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.ModelEnum;
import trackr.model.ReadOnlySupplierList;
import trackr.model.SupplierList;
import trackr.model.item.Item;
import trackr.model.person.Supplier;
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

        assertEquals(String.format(AddSupplierCommand.MESSAGE_SUCCESS, ModelEnum.SUPPLIER, validSupplier),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSupplier), modelStub.suppliersAdded);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        Supplier validSupplier = new SupplierBuilder().build();
        AddSupplierCommand addSupplierCommand = new AddSupplierCommand(validSupplier);
        ModelStub modelStub = new ModelStubWithSupplier(validSupplier);

        assertThrows(CommandException.class,
                String.format(AddSupplierCommand.MESSAGE_DUPLICATE_ITEM,
                        ModelEnum.SUPPLIER, ModelEnum.SUPPLIER), () -> addSupplierCommand.execute(modelStub));
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
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return this.supplier.isSameItem((Supplier) item);
        }
    }

    /**
     * A Model stub that always accept the Supplier being added.
     */
    private class ModelStubAcceptingSupplierAdded extends ModelStub {
        final ArrayList<Supplier> suppliersAdded = new ArrayList<>();

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return suppliersAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            suppliersAdded.add((Supplier) item);
        }

        @Override
        public ReadOnlySupplierList getSupplierList() {
            return new SupplierList();
        }
    }

}
