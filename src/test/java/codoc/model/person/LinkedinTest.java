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
        assertTrue(Linkedin.isValidLinkedin("linkedin.com/in/r4ch3t"));
        assertTrue(Linkedin.isValidLinkedin("linkedin.com/in/r4ch3t-with-hyphens")); // one character
        assertTrue(Linkedin.isValidLinkedin("linkedin.com/in/A-while-back-I-needed-to-count-the-amount-of"
                + "-letters-that-a-piece-of-text-in-an-email-template-had12")); // max characters
        // linkedin
    }
}
