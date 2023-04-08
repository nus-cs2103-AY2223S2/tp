package trackr.model.person;

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

import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.item.exceptions.ItemNotFoundException;
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
    public void add_duplicateSupplier_throwsDuplicateItemException() {
        uniqueSupplierList.add(ALICE);
        assertThrows(DuplicateItemException.class, () -> uniqueSupplierList.add(ALICE));
    }

    @Test
    public void setSupplier_nullTargetSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setItem(null, ALICE));
    }

    @Test
    public void setSupplier_nullEditedSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setItem(ALICE, null));
    }

    @Test
    public void setSupplier_targetSupplierNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueSupplierList.setItem(ALICE, ALICE));
    }

    @Test
    public void setSupplier_editedSupplierIsSamesupplier_success() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.setItem(ALICE, ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(ALICE);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasSameIdentity_success() {
        uniqueSupplierList.add(ALICE);
        Supplier editedAlice = new SupplierBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                                       .build();
        uniqueSupplierList.setItem(ALICE, editedAlice);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(editedAlice);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasDifferentIdentity_success() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.setItem(ALICE, BOB);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedSupplierHasNonUniqueIdentity_throwsDuplicatesupplierException() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.add(BOB);
        assertThrows(DuplicateItemException.class, () -> uniqueSupplierList.setItem(ALICE, BOB));
    }

    @Test
    public void remove_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.remove(null));
    }

    @Test
    public void remove_supplierDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueSupplierList.remove(ALICE));
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
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setItems((UniqueSupplierList) null));
    }

    @Test
    public void setSuppliers_uniqueSupplierList_replacesOwnListWithProvidedUniqueSupplierList() {
        uniqueSupplierList.add(ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        uniqueSupplierList.setItems(expectedUniqueSupplierList);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setItems((List<Supplier>) null));
    }

    @Test
    public void setSuppliers_list_replacesOwnListWithProvidedList() {
        uniqueSupplierList.add(ALICE);
        List<Supplier> supplierList = Collections.singletonList(BOB);
        uniqueSupplierList.setItems(supplierList);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BOB);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSuppliers_listWithDuplicatesuppliers_throwsDuplicatesupplierException() {
        List<Supplier> listWithDuplicatesuppliers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateItemException.class, () -> uniqueSupplierList.setItems(listWithDuplicatesuppliers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                        uniqueSupplierList.asUnmodifiableObservableList().remove(0));
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
