package seedu.careflow.model.drug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class PurposeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Purpose(null));
    }

    @Test
    public void constructor_invalidPurpose_throwsIllegalArgumentException() {
        String invalidPurpose = "";
        assertThrows(IllegalArgumentException.class, () -> new Purpose(invalidPurpose));
    }

    @Test
    public void isValidPurpose() {
        // null
        assertThrows(NullPointerException.class, () -> Purpose.isValidPurpose(null));

        // invalid purpose
        assertFalse(Purpose.isValidPurpose("")); // empty string
        assertFalse(Purpose.isValidPurpose(" ")); // spaces only
        // long description exceed 200 char limit
        assertFalse(Purpose.isValidPurpose(StringUtil.generateRandomString(1501)));

        // valid purpose
        assertTrue(Purpose.isValidPurpose("treat pain caused by fibromyalgia"));
        assertTrue(Purpose.isValidPurpose("a")); // one character
        assertTrue(Purpose.isValidPurpose("description * ()")); // with special character
        // long description with exactly 1500 char
        assertTrue(Purpose.isValidPurpose("a" + StringUtil.generateRandomString(1499)));
    }

    @Test
    public void equals() {
        Purpose purpose = new Purpose("treat mild to moderate pain,"
                + "moderate to severe pain in conjunction with opiates, or to reduce fever");
        // null -> returns false
        assertFalse(purpose.equals(null));

        // same object -> returns true
        assertTrue(purpose.equals(purpose));

        // same values -> returns true
        assertTrue(purpose.equals(new Purpose("treat mild to moderate pain,"
                + "moderate to severe pain in conjunction with opiates, or to reduce fever")));

        // different values -> return false
        assertFalse(purpose.equals(" treat attention deficit hyperactivity disorder (ADHD) "
                + "and narcolepsy. Adderall contains a combination of amphetamine and dextroamphetamine."));
    }
}
