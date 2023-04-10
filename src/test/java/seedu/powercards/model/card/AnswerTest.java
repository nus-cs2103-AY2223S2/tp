package seedu.powercards.model.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AnswerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test
    public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = "";
        assertThrows(IllegalArgumentException.class, () -> new Answer(invalidAnswer));
    }

    @Test
    public void isValidAnswer() {
        // null address
        assertThrows(NullPointerException.class, () -> Answer.isValidAnswer(null));

        // invalid addresses
        assertFalse(Answer.isValidAnswer("")); // empty string
        assertFalse(Answer.isValidAnswer(" ")); // spaces only

        // valid addresses
        assertTrue(Answer.isValidAnswer("A force of attraction between objects due to their mass"));
        assertTrue(Answer.isValidAnswer("-")); // one character
        assertTrue(Answer.isValidAnswer("The process by which plants convert sunlight into energy. "
                + "It generally involves the green pigment chlorophyll "
                + "and generates oxygen as a by-product.")); // long address
    }
}
