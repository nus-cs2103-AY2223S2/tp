package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTag() {
        // null tag
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        //valid tag of length 59
        String validTag = "thisIsAValidTagBecauseItHasALengthThatIsWithinTheLimitSixty";
        assertTrue(Tag.isValidTagName(validTag));
        assertTrue(Tag.isValidTagLength(validTag));

        // valid tag of length 60
        validTag = "thisIsAValidTagBecauseItHasALengthThatIsWithinTheLimitSixty2";
        assertTrue(Tag.isValidTagName(validTag));
        assertTrue(Tag.isValidTagLength(validTag));

        // invalid tag with non-alphanumeric character
        String invalidTag = "thisIsAnInvalidTagBecauseOfANon-AlphanumericCharacter";
        assertFalse(Tag.isValidTagName(invalidTag)); // invalid tag because contains non-alphanumeric character
        assertTrue(Tag.isValidTagLength(invalidTag)); // valid tag length

        // invalid tag of length 61
        invalidTag = "thisIsAnInvalidTagBecauseItIsVeryLongWithALengthMoreThanSixty";
        assertTrue(Tag.isValidTagName(invalidTag));
        assertFalse(Tag.isValidTagLength(invalidTag)); // invalid tag because exceed maximum length
    }

}
