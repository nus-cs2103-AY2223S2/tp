package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalDrugs.PROZAC;
import static seedu.careflow.testutil.TypicalDrugs.VALID_ACTIVE_INGREDIENT_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_DIRECTION_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_PURPOSE_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_SIDE_EFFECT_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_STORAGE_COUNT_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VALID_TRADE_NAME_VISINE;
import static seedu.careflow.testutil.TypicalDrugs.VISINE;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.DrugBuilder;

public class DrugTest {
    @Test
    public void constructor_nullValueInRequiredField_throwsNullPointerException() {
        DrugBuilder prozac = new DrugBuilder(PROZAC);
        assertThrows(NullPointerException.class, () -> prozac.withTradeName(null).build());
        assertThrows(NullPointerException.class, () -> prozac.withActiveIngredient(null).build());
        assertThrows(NullPointerException.class, () -> prozac.withDirection(null).build());
        assertThrows(NullPointerException.class, () -> prozac.withPurpose(null).build());
        assertThrows(NullPointerException.class, () -> prozac.withSideEffect(null).build());
        assertThrows(NullPointerException.class, () -> prozac.withStorageCount(null).build());
    }

    @Test
    public void isSameDrug() {
        // same object -> returns true
        assertTrue(PROZAC.isSameDrug(PROZAC));

        // null -> returns false
        assertFalse(PROZAC.isSameDrug(null));

        // same trade name, one attributes different -> returns true
        Drug editedProzac = new DrugBuilder(PROZAC).withStorageCount(VALID_STORAGE_COUNT_VISINE).build();
        assertTrue(PROZAC.isSameDrug(editedProzac));

        // same trade name, all others attributes different -> returns true
        editedProzac = new DrugBuilder(PROZAC).withActiveIngredient(VALID_ACTIVE_INGREDIENT_VISINE)
                .withPurpose(VALID_PURPOSE_VISINE).withDirection(VALID_DIRECTION_VISINE)
                .withSideEffect(VALID_SIDE_EFFECT_VISINE).withStorageCount(VALID_STORAGE_COUNT_VISINE).build();
        assertTrue(PROZAC.isSameDrug(editedProzac));


        // different trade name, all other attributes same -> returns false
        editedProzac = new DrugBuilder(PROZAC).withTradeName(VALID_TRADE_NAME_VISINE).build();
        assertFalse(PROZAC.isSameDrug(editedProzac));

        // trade name differs in case, all other attributes same -> returns false
        Drug editedVisine = new DrugBuilder(VISINE).withTradeName(VALID_TRADE_NAME_VISINE.toLowerCase()).build();
        assertFalse(VISINE.isSameDrug(editedVisine));

        // trade name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TRADE_NAME_VISINE + " ";
        editedVisine = new DrugBuilder(VISINE).withTradeName(nameWithTrailingSpaces).build();
        assertFalse(VISINE.isSameDrug(editedVisine));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Drug prozacCopy = new DrugBuilder(PROZAC).build();
        assertTrue(PROZAC.equals(prozacCopy));

        // same object -> returns true
        assertTrue(PROZAC.equals(PROZAC));

        // null -> returns false
        assertFalse(PROZAC.equals(null));

        // different type -> returns false
        assertFalse(PROZAC.equals(5));

        // different person -> returns false
        assertFalse(PROZAC.equals(VISINE));

        // different trade name -> returns false
        Drug editedAlice = new DrugBuilder(PROZAC).withTradeName(VALID_TRADE_NAME_VISINE).build();
        assertFalse(PROZAC.equals(editedAlice));

        // different active ingredient -> returns false
        editedAlice = new DrugBuilder(PROZAC).withActiveIngredient(VALID_ACTIVE_INGREDIENT_VISINE).build();
        assertFalse(PROZAC.equals(editedAlice));

        // different purpose -> returns false
        editedAlice = new DrugBuilder(PROZAC).withPurpose(VALID_PURPOSE_VISINE).build();
        assertFalse(PROZAC.equals(editedAlice));

        // different direction -> returns false
        editedAlice = new DrugBuilder(PROZAC).withDirection(VALID_DIRECTION_VISINE).build();
        assertFalse(PROZAC.equals(editedAlice));

        // different side effect -> returns false
        editedAlice = new DrugBuilder(PROZAC).withSideEffect(VALID_SIDE_EFFECT_VISINE).build();
        assertFalse(PROZAC.equals(editedAlice));

        // different storage count -> returns false
        editedAlice = new DrugBuilder(PROZAC).withStorageCount(VALID_STORAGE_COUNT_VISINE).build();
        assertFalse(PROZAC.equals(editedAlice));
    }
}
