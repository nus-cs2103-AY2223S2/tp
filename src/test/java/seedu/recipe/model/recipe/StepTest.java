package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StepTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Step(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidStep = "";
        assertThrows(IllegalArgumentException.class, () -> new Step(invalidStep));
    }

    @Test
    public void isValidStep() {
        // null address
        assertThrows(NullPointerException.class, () -> Step.isValidStep(null));

        // invalid name
        assertFalse(Step.isValidStep("")); // empty string
        assertFalse(Step.isValidStep(" ")); // spaces only
        assertFalse(Step.isValidStep("^")); // only non-alphanumeric characters
        assertFalse(Step.isValidStep("wash*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Step.isValidStep("wash")); // alphabets only
        assertTrue(Step.isValidStep("12345")); // numbers only
        assertTrue(Step.isValidStep("wash 2 times")); // alphanumeric characters
        assertTrue(Step.isValidStep("Capital Potato")); // with capital letters
        assertTrue(Step.isValidStep("Wash potatoes with warm water")); // long names
    }
}
