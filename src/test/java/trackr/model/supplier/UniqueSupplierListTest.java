package trackr.model.supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalSuppliers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.model.supplier.exceptions.DuplicateSupplierException;
import trackr.model.supplier.exceptions.SupplierNotFoundException;
import trackr.testutil.SupplierBuilder;

public class UniqueSupplierListTest {

    private final UniqueSupplierList uniqueSupplierList = new UniqueSupplierList();

    @Test
    public void contains_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.contains(null));
    }

    @Test
    public void contains_supplierNotInList_returnsFalse() {
        assertFalse(uniqueSupplierList.contains(ALICE));
    }

    @Test
    public void contains_supplierInList_returnsTrue() {
        uniqueSupplierList.add(ALICE);
        assertTrue(uniqueSupplierList.contains(ALICE));
    }

    @Test
    public void contains_supplierWithSameIdentityFieldsInList_returnsTrue() {
        uniqueSupplierList.add(ALICE);
        Supplier editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueSupplierList.contains(editedAlice));
    }

    @Test
    public void add_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.add(null));
    }

    @Test
    public void add_duplicateSupplier_throwsDuplicateSupplierException() {
        uniqueSupplierList.add(ALICE);
        assertThrows(DuplicateSupplierException.class, () -> uniqueSupplierList.add(ALICE));
    }

    @Test
    public void setSupplier_nullTargetSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSupplier(null, ALICE));
    }

    @Test
    public void setSupplier_nullEditedSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSupplier(ALICE, null));
    }

    @Test
    public void setSupplier_targetSupplierNotInList_throwsSupplierNotFoundException() {
        assertThrows(SupplierNotFoundException.class, () -> uniqueSupplierList.setSupplier(ALICE, ALICE));
    }

    @Test
    public void setSupplier_editedSupplierIsSamesupplier_success() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.setSupplier(ALICE, ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(ALICE);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasSameIdentity_success() {
        uniqueSupplierList.add(ALICE);
        Supplier editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueSupplierList.setSupplier(ALICE, editedAlice);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(editedAlice);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasDifferentIdentity_success() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.setSupplier(ALICE, BOB);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasNonUniqueIdentity_throwsDuplicatesupplierException() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.add(BOB);
        assertThrows(DuplicateSupplierException.class, () -> uniqueSupplierList.setSupplier(ALICE, BOB));
    }

    @Test
    public void remove_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.remove(null));
    }

    @Test
    public void remove_supplierDoesNotExist_throwsSupplierNotFoundException() {
        assertThrows(SupplierNotFoundException.class, () -> uniqueSupplierList.remove(ALICE));
    }

    @Test
    public void remove_existingSupplier_removesSupplier() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.remove(ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_nullUniqueSupplierList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSuppliers((UniqueSupplierList) null));
    }

    @Test
    public void setSuppliers_uniqueSupplierList_replacesOwnListWithProvidedUniqueSupplierList() {
        uniqueSupplierList.add(ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        uniqueSupplierList.setSuppliers(expectedUniqueSupplierList);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setSuppliers((List<Supplier>) null));
    }

    @Test
    public void setSuppliers_list_replacesOwnListWithProvidedList() {
        uniqueSupplierList.add(ALICE);
        List<Supplier> supplierList = Collections.singletonList(BOB);
        uniqueSupplierList.setSuppliers(supplierList);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_listWithDuplicatesuppliers_throwsDuplicatesupplierException() {
        List<Supplier> listWithDuplicatesuppliers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateSupplierException.class, () -> uniqueSupplierList
                                                                    .setSuppliers(listWithDuplicatesuppliers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueSupplierList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        uniqueSupplierList.add(ALICE);

        UniqueSupplierList differentUniqueSupplierList = new UniqueSupplierList();
        differentUniqueSupplierList.add(BOB);

        UniqueSupplierList sameUniqueSupplierList = new UniqueSupplierList();
        sameUniqueSupplierList.add(ALICE);

        assertTrue(uniqueSupplierList.equals(uniqueSupplierList)); //same object
        assertTrue(uniqueSupplierList.equals(sameUniqueSupplierList)); //contains the same suppliers

        assertFalse(uniqueSupplierList.equals(null)); //null
        assertFalse(uniqueSupplierList.equals(differentUniqueSupplierList)); //different supplier lists
        assertFalse(uniqueSupplierList.equals(1)); //different objects
    }
}
