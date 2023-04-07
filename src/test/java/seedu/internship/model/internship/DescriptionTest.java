package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void isEmpty() {
        // empty description
        Description emptyDescription = new Description("");
        assertTrue(emptyDescription.isEmpty());

        // description with only whitespace
        Description emptySpaceDescription = new Description("   ");
        assertTrue(emptySpaceDescription.isEmpty());

        // valid description
        Description validDescription = new Description("Test test test");
        System.out.println(validDescription.isEmpty());
        assertFalse(validDescription.isEmpty());
    }

}
