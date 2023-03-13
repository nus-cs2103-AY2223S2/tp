package codoc.model.person;

import static codoc.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LinkedinTest {

    @Test
    public void constructor_invalidLinkedin_throwsIllegalArgumentException() {
        String invalidLinkedin = "";
        assertThrows(IllegalArgumentException.class, () -> new Linkedin(invalidLinkedin));
    }

    @Test
    public void isValidLinkedin() {
        // null linkedin
        assertThrows(NullPointerException.class, () -> Linkedin.isValidLinkedin(null));

        // invalid linkedins
        assertFalse(Linkedin.isValidLinkedin("")); // empty string
        assertFalse(Linkedin.isValidLinkedin(" ")); // spaces only

        // valid linkedins
        assertTrue(Linkedin.isValidLinkedin("Blk 456, Den Road, #01-355"));
        assertTrue(Linkedin.isValidLinkedin("-")); // one character
        assertTrue(Linkedin.isValidLinkedin("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long
        // linkedin
    }
}
