package seedu.careflow.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalDrugs.PROZAC;
import static seedu.careflow.testutil.TypicalDrugs.VALID_DIRECTION_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_PURPOSE_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.exceptions.DuplicateDrugException;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.testutil.DrugBuilder;


public class DrugInventoryTest {
    private final DrugInventory drugInventory = new DrugInventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), drugInventory.getDrugList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> drugInventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDrugInventory_replacesData() {
        DrugInventory newData = getTypicalDrugInventory();
        drugInventory.resetData(newData);
        assertEquals(newData, drugInventory);
    }

    @Test
    public void resetData_withDuplicateDrugs_throwsDuplicateDrugException() {
        // Two drugs with the same identity fields
        Drug editedProzac = new DrugBuilder(PROZAC)
                .withDirection(VALID_DIRECTION_VISINE)
                .withPurpose(VALID_PURPOSE_VISINE)
                .build();
        List<Drug> newDrugs = Arrays.asList(PROZAC, editedProzac);
        DrugInventoryTest.DrugInventoryStub newData = new DrugInventoryTest.DrugInventoryStub(newDrugs);

        assertThrows(DuplicateDrugException.class, () -> drugInventory.resetData(newData));
    }

    @Test
    public void hasDrug_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> drugInventory.hasDrug(null));
    }

    @Test
    public void hasDrug_drugNotInDrugInventory_returnsFalse() {
        assertFalse(drugInventory.hasDrug(PROZAC));
    }

    @Test
    public void hasDrug_drugInDrugInventory_returnsTrue() {
        drugInventory.addDrug(PROZAC);
        assertTrue(drugInventory.hasDrug(PROZAC));
    }

    @Test
    public void hasDrug_drugWithSameIdentityFieldsInDrugInventory_returnsTrue() {
        drugInventory.addDrug(PROZAC);
        Drug editedProzac = new DrugBuilder(PROZAC).withDirection(VALID_DIRECTION_VISINE)
                .withPurpose(VALID_PURPOSE_VISINE)
                .build();
        assertTrue(drugInventory.hasDrug(editedProzac));
    }

    @Test
    public void getDrugList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> drugInventory.getDrugList().remove(0));
    }

    /**
     * A stub ReadOnlyDrugInventory whose drugs list can violate interface constraints.
     */
    private static class DrugInventoryStub implements ReadOnlyDrugInventory {
        private final ObservableList<Drug> drugs = FXCollections.observableArrayList();

        DrugInventoryStub(Collection<Drug> drugs) {
            this.drugs.setAll(drugs);
        }

        @Override
        public ObservableList<Drug> getDrugList() {
            return drugs;
        }
    }
}
