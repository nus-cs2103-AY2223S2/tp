package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.supplier.Supplier;
import trackr.model.supplier.exceptions.DuplicateSupplierException;
import trackr.testutil.SupplierBuilder;

public class SupplierListTest {

    private final SupplierList supplierList = new SupplierList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), supplierList.getSupplierList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> supplierList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlysupplierList_replacesData() {
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
        supplierListStub newData = new supplierListStub(newSuppliers);

        assertThrows(DuplicateSupplierException.class, () -> supplierList.resetData(newData));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> supplierList.hasSupplier(null));
    }

    @Test
    public void hasSupplier_supplierNotInsupplierList_returnsFalse() {
        assertFalse(supplierList.hasSupplier(ALICE));
    }

    @Test
    public void hasSupplier_supplierInSupplierList_returnsTrue() {
        supplierList.addSupplier(ALICE);
        assertTrue(supplierList.hasSupplier(ALICE));
    }

    @Test
    public void hasSupplier_supplierWithSameIdentityFieldsInsupplierList_returnsTrue() {
        supplierList.addSupplier(ALICE);
        Supplier editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(supplierList.hasSupplier(editedAlice));
    }

    @Test
    public void getSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> supplierList.getSupplierList().remove(0));
    }

    /**
     * A stub ReadOnlysupplierList whose suppliers list can violate interface constraints.
     */
    private static class supplierListStub implements ReadOnlySupplierList {
        private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

        supplierListStub(Collection<Supplier> suppliers) {
            this.suppliers.setAll(suppliers);
        }

        @Override
        public ObservableList<Supplier> getSupplierList() {
            return suppliers;
        }
    }

}
