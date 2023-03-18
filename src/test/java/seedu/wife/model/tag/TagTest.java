package seedu.wife.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.wife.testutil.Assert.assertThrows;

import javafx.scene.control.TableFocusModel;
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
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> TagName.isValidTagName(null));
    }

    @Test
    public void isValidStringRepresentation() {
        Tag testTag = new Tag("test");
        assertEquals("[Test]", testTag.toString());
    }
}
