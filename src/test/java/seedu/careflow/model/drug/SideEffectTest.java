package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class SideEffectTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SideEffect(null));
    }

    @Test
    public void constructor_invalidSideEffect_throwsIllegalArgumentException() {
        String invalidSideEffect = "";
        assertThrows(IllegalArgumentException.class, () -> new SideEffect(invalidSideEffect));
    }

    @Test
    public void isValidSideEffect() {
        // null
        assertThrows(NullPointerException.class, () -> SideEffect.isValidSideEffect(null));

        // invalid side effect
        assertFalse(SideEffect.isValidSideEffect("")); // empty string
        assertFalse(SideEffect.isValidSideEffect(" ")); // spaces only
        // long description exceed 200 char limit
        assertFalse(SideEffect.isValidSideEffect(StringUtil.generateRandomString(1501)));

        // valid side effect
        assertTrue(SideEffect.isValidSideEffect("Lyrica can cause a severe allergic reaction"));
        assertTrue(SideEffect.isValidSideEffect("a")); // one character
        assertTrue(SideEffect.isValidSideEffect("description * ()")); // with special character
        // long description with exactly 1500 char
        assertTrue(SideEffect.isValidSideEffect("a" + StringUtil.generateRandomString(1499)));
    }

    @Test
    public void equals() {
        SideEffect sideEffect = new SideEffect("You should not use amitriptyline"
                + " if you have recently had a heart attack");
        // null -> returns false
        assertFalse(sideEffect.equals(null));

        // same object -> returns true
        assertTrue(sideEffect.equals(sideEffect));

        // same values -> returns true
        assertTrue(sideEffect.equals(new SideEffect("You should not use amitriptyline"
                + " if you have recently had a heart attack")));

        // different values -> return false
        assertFalse(sideEffect.equals("Do not use amitriptyline if you have used an MAO inhibitor"
                + " in the past 14 days, such as isocarboxazid, linezolid, methylene blue injection,"
                + " phenelzine, rasagiline, selegiline, or tranylcypromine."));
    }
}
