package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobDescription(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidJobDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new JobDescription(invalidJobDescription));
    }

    @Test
    public void isValidJobDescription() {
        // null address
        assertThrows(NullPointerException.class, () -> JobDescription.isValidJobDescription(null));

        // invalid addresses
        assertFalse(JobDescription.isValidJobDescription("")); // empty string
        assertFalse(JobDescription.isValidJobDescription(" ")); // spaces only

        // valid addresses
        assertTrue(JobDescription.isValidJobDescription("SWE At Google, prefers penultimate"));
        assertTrue(JobDescription.isValidJobDescription("-")); // one character
        assertTrue(JobDescription.isValidJobDescription("Sed ut perspiciatis unde omnis iste natus error sit + " +
                "voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo " +
                "inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.")); // long jd
    }
}
