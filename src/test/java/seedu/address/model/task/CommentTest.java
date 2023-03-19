package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    public void constructor_invalidComment_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Comment(invalidDate));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Comment.isValidComment(null));

        // invalid addresses
        assertFalse(Comment.isValidComment("")); // empty string
        assertFalse(Comment.isValidComment(" ")); // spaces only

        // valid addresses
        assertTrue(Comment.isValidComment("done"));
        assertTrue(Comment.isValidComment("VERY WELL DONE")); // different format of date
        assertTrue(Comment.isValidComment("DONE very WELL 11029329 points")); // another different format of date
    }
}
