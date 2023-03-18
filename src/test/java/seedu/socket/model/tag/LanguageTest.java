package seedu.socket.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_CPLUSPLUS;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_LANGUAGE_PYTHON;
import static seedu.socket.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.socket.model.person.tag.Language;

class LanguageTest {
    private static final Language AMY_LANGUAGE = new Language(VALID_LANGUAGE_PYTHON);
    private static final Language BOB_LANGUAGE = new Language(VALID_LANGUAGE_CPLUSPLUS);
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Language(null));
    }

    @Test
    public void constructor_invalidLanguageName_throwsIllegalArgumentException() {
        String invalidLanguageName = "";
        assertThrows(IllegalArgumentException.class, () -> new Language(invalidLanguageName));
    }

    @Test
    public void testIsValidLanguageName() {
        // null language name
        assertThrows(NullPointerException.class, () -> Language.isValidLanguageName(null));

        // invalid language name
        assertFalse(Language.isValidLanguageName("")); // empty string
        assertFalse(Language.isValidLanguageName(" ")); // spaces only
        assertFalse(Language.isValidLanguageName("^")); // only invalid special characters
        assertFalse(Language.isValidLanguageName("1")); // numbers only
        assertFalse(Language.isValidLanguageName("-")); // starts with valid special characters
        assertFalse(Language.isValidLanguageName("+")); // starts with valid special characters
        assertFalse(Language.isValidLanguageName("#")); // starts with valid special characters

        // valid language name
        assertTrue(Language.isValidLanguageName("C")); // single alphabet only
        assertTrue(Language.isValidLanguageName("Python")); // alphabets only
        assertTrue(Language.isValidLanguageName("C--")); // alphabets and valid special characters
        assertTrue(Language.isValidLanguageName("C++")); // alphabets and valid special characters
        assertTrue(Language.isValidLanguageName("C#")); // alphabets and valid special characters
        assertTrue(Language.isValidLanguageName("X10")); // alphanumeric characters
        assertTrue(Language.isValidLanguageName("abc-10")); // alphanumeric characters and valid special characters
    }

    @Test
    public void testToString() {
        assertTrue(("[" + VALID_LANGUAGE_PYTHON + "]").equals(AMY_LANGUAGE.toString()));
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        Language amyCopy = new Language(VALID_LANGUAGE_PYTHON);
        assertTrue(AMY_LANGUAGE.equals(amyCopy));

        // same object -> returns true
        assertTrue(AMY_LANGUAGE.equals(AMY_LANGUAGE));

        // null -> returns false
        assertFalse(AMY_LANGUAGE.equals(null));

        // different type -> returns false
        assertFalse(AMY_LANGUAGE.equals(5));

        // different language -> returns false
        assertFalse(AMY_LANGUAGE.equals(BOB_LANGUAGE));
    }
}
