package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    public void constructor_invalidComment_throwsIllegalArgumentException() {
        String invalidComment = "";
        assertThrows(IllegalArgumentException.class, () -> new Comment(invalidComment));
    }

    @Test
    public void isValidComment() {
        //Invalid Comments
        assertThrows(NullPointerException.class, () -> Comment.isValidComment(null));
        assertFalse(Comment.isValidComment("")); // empty string

        //Valid Comments
        assertTrue(Comment.isValidComment("  hello")); //with leading white space
        assertTrue(Comment.isValidComment("hello    ")); //with trailing white space
        assertTrue(Comment.isValidComment("A1b2")); //alphanumeric
        assertTrue(Comment.isValidComment("I love C#!_?")); //with symbols
    }

    @Test
    public void checkCommentEquality() {
        Comment commentOne = new Comment("hello");
        Comment commentOneDuplicated = new Comment("hello");
        Comment commentTwo = new Comment("bye");

        //Same object
        assertEquals(commentOne, commentOne);

        //Different object but same content
        assertEquals(commentOne, commentOneDuplicated);

        //One Comment and one non-Comment object
        assertNotEquals(commentOne, "hello");

        //Comment objects with different comment content
        assertNotEquals(commentOne, commentTwo);
    }

    @Test
    public void checkStringRepresentation() {
        Comment commentOne = new Comment("hello");
        assertEquals(commentOne.toString(), "[hello]");
    }

    @Test
    public void hashcode() {
        //Same string
        Comment commentOne = new Comment("hello");
        Comment commentOneDuplicate = new Comment("hello");
        Comment commentTwo = new Comment("Hello");
        Comment commentThree = new Comment("helloThere!");

        // same string -> equal
        assertEquals(commentOne.hashCode(), commentOneDuplicate.hashCode());

        // different capitalisation -> not equal
        assertNotEquals(commentOne.hashCode(), commentTwo.hashCode());

        // different strings -> not equal
        assertNotEquals(commentOne.hashCode(), commentThree.hashCode());
    }

}
