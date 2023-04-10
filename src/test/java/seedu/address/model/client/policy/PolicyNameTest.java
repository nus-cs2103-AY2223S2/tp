package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PolicyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Frequency(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidPolicyName = "";
        assertThrows(IllegalArgumentException.class, () -> new PolicyName(invalidPolicyName));
    }

    @Test
    void isValidName() {
        String invalid1 = "     ";
        String invalid2 = "./.!@##$";
        String invaild3 = "im a beast./1/./.";
        String valid = "Health";

        assertFalse(PolicyName.isValidName(invalid1));
        assertFalse(PolicyName.isValidName(invalid2));
        assertFalse(PolicyName.isValidName(invaild3));
        assertTrue(PolicyName.isValidName(valid));
    }

    @Test
    void isValidNameEnum() {
        String invalid1 = "random";
        String invalid2 = "Fire insurante";
        String valid = "Fire Insurance";
        String valid2 = "fire insurance";
        String valid3 = "FIRE INSURANCE";

        assertFalse(PolicyName.isValidNameEnum(invalid1));
        assertFalse(PolicyName.isValidNameEnum(invalid2));
        assertTrue(PolicyName.isValidNameEnum(valid));
        assertTrue(PolicyName.isValidNameEnum(valid2));
        assertTrue(PolicyName.isValidNameEnum(valid3));
    }
}
