package seedu.powercards.model.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test
    public void constructor_invalidQuestion_throwsIllegalArgumentException() {
        String invalidQuestion = "";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidQuestion));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid name
        assertFalse(Question.isValidQuestion("")); // empty string
        assertFalse(Question.isValidQuestion(" ")); // spaces only

        // valid name
        assertTrue(Question.isValidQuestion("what is gravity")); // alphabets only
        assertTrue(Question.isValidQuestion("98")); // numbers only
        assertTrue(Question.isValidQuestion("What is 1 plus 1")); // alphanumeric characters
        assertTrue(Question.isValidQuestion("Who is Isaac Newton")); // with capital letters
        assertTrue(Question.isValidQuestion("Why does the moon's gravity cause tides on earth but"
                + "the Sun's gravity does not?")); // long question
    }
}
