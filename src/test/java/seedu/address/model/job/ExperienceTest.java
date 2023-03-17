package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExperienceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Experience(null));
    }

    @Test
    public void constructor_invalidExperience_throwsIllegalArgumentException() {
        String invalidExperience = "";
        assertThrows(IllegalArgumentException.class, () -> new Experience(invalidExperience));
    }

    @Test
    public void isValidExperience() {
        // null name
        assertThrows(NullPointerException.class, () -> Experience.isValidExperience(null));

        // invalid name
        assertFalse(Experience.isValidExperience("")); // empty string
        assertFalse(Experience.isValidExperience(" ")); // spaces only

        // valid name
        assertTrue(Experience.isValidExperience("Year")); // alphabets only
        assertTrue(Experience.isValidExperience("12345")); // numbers only
        assertTrue(Experience.isValidExperience("C 2 Years")); // alphanumeric characters
        assertTrue(Experience.isValidExperience("C Years")); // with capital letters
        assertTrue(Experience.isValidExperience("C 2 Years I am the best")); // long experience
    }
}
