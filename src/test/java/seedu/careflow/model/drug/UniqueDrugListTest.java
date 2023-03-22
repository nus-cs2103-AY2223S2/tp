package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalDrugs.PROZAC;
import static seedu.careflow.testutil.TypicalDrugs.VALID_DIRECTION_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_PURPOSE_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_SIDE_EFFECT_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_STORAGE_COUNT_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VISINE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.drug.exceptions.DrugNotFoundException;
import seedu.careflow.model.drug.exceptions.DuplicateDrugException;
import seedu.careflow.testutil.DrugBuilder;


public class UniqueDrugListTest {
    private final UniqueDrugList uniqueDrugList = new UniqueDrugList();

    @Test
    public void contains_nullDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.contains(null));
    }

    @Test
    public void contains_drugNotInList_returnsFalse() {
        assertFalse(uniqueDrugList.contains(PROZAC));
    }

    @Test
    public void contains_drugInList_returnsTrue() {
        uniqueDrugList.add(PROZAC);
        assertTrue(uniqueDrugList.contains(PROZAC));
    }

    @Test
    public void contains_drugWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDrugList.add(PROZAC);
        Drug editedProzac = new DrugBuilder(PROZAC).withPurpose(VALID_PURPOSE_VISINE)
                .withDirection(VALID_DIRECTION_VISINE).build();
        assertTrue(uniqueDrugList.contains(editedProzac));
    }

    @Test
    public void add_nullDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.add(null));
    }

    @Test
    public void add_duplicateDrug_throwsDuplicateDrugException() {
        uniqueDrugList.add(PROZAC);
        assertThrows(DuplicateDrugException.class, () -> uniqueDrugList.add(PROZAC));
    }

    @Test
    public void setDrug_nullTargetDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.setDrug(null, PROZAC));
    }

    @Test
    public void setDrug_nullEditedDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.setDrug(PROZAC, null));
    }

    @Test
    public void setDrug_targetDrugNotInList_throwsDrugNotFoundException() {
        assertThrows(DrugNotFoundException.class, () -> uniqueDrugList.setDrug(PROZAC, PROZAC));
    }

    @Test
    public void setDrug_editedDrugIsSameDrug_success() {
        uniqueDrugList.add(PROZAC);
        uniqueDrugList.setDrug(PROZAC, PROZAC);
        UniqueDrugList expectedUniqueDrugList = new UniqueDrugList();
        expectedUniqueDrugList.add(PROZAC);
        assertEquals(expectedUniqueDrugList, uniqueDrugList);
    }

    @Test
    public void setDrug_editedDrugHasSameIdentity_success() {
        uniqueDrugList.add(PROZAC);
        Drug editedProzac = new DrugBuilder(PROZAC)
                .withStorageCount(VALID_STORAGE_COUNT_VISINE)
                .withSideEffect(VALID_SIDE_EFFECT_VISINE)
                .build();
        uniqueDrugList.setDrug(PROZAC, editedProzac);
        UniqueDrugList expectedUniqueDrugList = new UniqueDrugList();
        expectedUniqueDrugList.add(editedProzac);
        assertEquals(expectedUniqueDrugList, uniqueDrugList);
    }

    @Test
    public void setDrug_editedDrugHasDifferentIdentity_success() {
        uniqueDrugList.add(PROZAC);
        uniqueDrugList.setDrug(PROZAC, VISINE);
        UniqueDrugList expectedUniqueDrugList = new UniqueDrugList();
        expectedUniqueDrugList.add(VISINE);
        assertEquals(expectedUniqueDrugList, uniqueDrugList);
    }

    @Test
    public void setDrug_editedDrugHasNonUniqueIdentity_throwsDuplicateDrugException() {
        uniqueDrugList.add(PROZAC);
        uniqueDrugList.add(VISINE);
        assertThrows(DuplicateDrugException.class, () -> uniqueDrugList.setDrug(PROZAC, VISINE));
    }

    @Test
    public void remove_nullDrug_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.remove(null));
    }

    @Test
    public void remove_drugDoesNotExist_throwsDrugNotFoundException() {
        assertThrows(DrugNotFoundException.class, () -> uniqueDrugList.remove(PROZAC));
    }

    @Test
    public void remove_existingDrug_removesDrug() {
        uniqueDrugList.add(PROZAC);
        uniqueDrugList.remove(PROZAC);
        UniqueDrugList expectedUniqueDrugList = new UniqueDrugList();
        assertEquals(expectedUniqueDrugList, uniqueDrugList);
    }

    @Test
    public void setDrugs_nullUniqueDrugList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.setDrugs((UniqueDrugList) null));
    }

    @Test
    public void setDrugs_uniqueDrugList_replacesOwnListWithProvidedUniqueDrugList() {
        uniqueDrugList.add(PROZAC);
        UniqueDrugList expectedUniqueDrugList = new UniqueDrugList();
        expectedUniqueDrugList.add(VISINE);
        uniqueDrugList.setDrugs(expectedUniqueDrugList);
        assertEquals(expectedUniqueDrugList, uniqueDrugList);
    }

    @Test
    public void setDrugs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDrugList.setDrugs((List<Drug>) null));
    }

    @Test
    public void setDrugs_list_replacesOwnListWithProvidedList() {
        uniqueDrugList.add(PROZAC);
        List<Drug> drugList = Collections.singletonList(VISINE);
        uniqueDrugList.setDrugs(drugList);
        UniqueDrugList expectedUniqueDrugList = new UniqueDrugList();
        expectedUniqueDrugList.add(VISINE);
        assertEquals(expectedUniqueDrugList, uniqueDrugList);
    }

    @Test
    public void setDrugs_listWithDuplicateDrugs_throwsDuplicateDrugException() {
        List<Drug> listWithDuplicateDrugs = Arrays.asList(PROZAC, PROZAC);
        assertThrows(DuplicateDrugException.class, () -> uniqueDrugList.setDrugs(listWithDuplicateDrugs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDrugList.asUnmodifiableObservableList().remove(0));
    }
}
