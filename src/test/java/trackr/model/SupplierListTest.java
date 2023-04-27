package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalSuppliers.BOB;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.person.Supplier;
import trackr.testutil.SupplierBuilder;

//@@author arkarsg-reused
public class SupplierListTest {

    private final SupplierList supplierList = new SupplierList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), supplierList.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> supplierList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySupplierList_replacesData() {
        SupplierList newData = getTypicalSupplierList();
        supplierList.resetData(newData);
        assertEquals(newData, supplierList);
    }

    @Test
    public void resetData_withDuplicateSuppliers_throwsDuplicateSupplierException() {
        // Two suppliers with the same identity fields
        Supplier editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Supplier> newSuppliers = Arrays.asList(ALICE, editedAlice);
        SupplierListStub newData = new SupplierListStub(newSuppliers);

        assertThrows(DuplicateItemException.class, () -> supplierList.resetData(newData));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> supplierList.hasItem(null));
    }

    @Test
    public void hasSupplier_supplierNotInSupplierList_returnsFalse() {
        assertFalse(supplierList.hasItem(ALICE));
    }

    @Test
    public void hasSupplier_supplierInSupplierList_returnsTrue() {
        supplierList.addItem(ALICE);
        assertTrue(supplierList.hasItem(ALICE));
    }

    @Test
    public void hasSupplier_supplierWithSameIdentityFieldsInSupplierList_returnsTrue() {
        supplierList.addItem(ALICE);
        Supplier editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(supplierList.hasItem(editedAlice));
    }

    @Test
    public void getSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> supplierList.getItemList().remove(0));
    }

    //@@author liumc-sg-reused
    @Test
    public void equals() {
        supplierList.addItem(ALICE);

        SupplierList differentSupplierList = new SupplierList();
        differentSupplierList.addItem(BOB);

        SupplierList sameSupplierList = new SupplierList();
        sameSupplierList.addItem(ALICE);

        assertTrue(supplierList.equals(supplierList)); //same object
        assertTrue(supplierList.equals(sameSupplierList)); //contains the same suppliers

        assertFalse(supplierList.equals(null)); //null
        assertFalse(supplierList.equals(differentSupplierList)); //different supplier list
        assertFalse(supplierList.equals(1)); //different objects
    }
    //@@author

    /**
     * A stub ReadOnlySupplierList whose suppliers list can violate interface constraints.
     */
    private static class SupplierListStub implements ReadOnlySupplierList {
        private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

        SupplierListStub(Collection<Supplier> suppliers) {
            this.suppliers.setAll(suppliers);
        }

        @Override
        public ObservableList<Supplier> getItemList() {
            return suppliers;
        }
    }

}
