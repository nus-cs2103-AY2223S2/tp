package mycelium.mycelium.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class NonEmptyStringTest {

    @Test
    void getValue() {
        NonEmptyString s = NonEmptyString.of("foo");
        assertEquals("foo", s.getValue());
    }

    @Test
    void testToString() {
        NonEmptyString s = NonEmptyString.of("foo");
        assertEquals("foo", s.toString());
    }

    @Test
    void ofOptional_nonEmptyString_returnSome() {
        Optional<NonEmptyString> s = NonEmptyString.ofOptional("foo");
        assertEquals(Optional.of(NonEmptyString.of("foo")), s);
    }

    @Test
    void ofOptional_emptyString_returnsEmpty() {
        Optional<NonEmptyString> s = NonEmptyString.ofOptional("");
        assertEquals(Optional.empty(), s);
    }

    @Test
    void ofOptional_null_returnsEmpty() {
        Optional<NonEmptyString> s = NonEmptyString.ofOptional(null);
        assertEquals(Optional.empty(), s);
    }

    @Test
    void isValid() {
        assertTrue(NonEmptyString.isValid("foo"));
        assertFalse(NonEmptyString.isValid(""));
        assertFalse(NonEmptyString.isValid(null));
    }

    @Test
    void isValid_unicodeZeroWidthChars() {
        assertFalse(NonEmptyString.isValid("\u200B"));
        assertFalse(NonEmptyString.isValid("\u200C"));
        assertFalse(NonEmptyString.isValid("\u200D"));
        assertFalse(NonEmptyString.isValid("\uFEFF"));

        assertTrue(NonEmptyString.isValid("foo\u200B"));
        assertTrue(NonEmptyString.isValid("foo\u200C"));
        assertTrue(NonEmptyString.isValid("foo\u200D"));
        assertTrue(NonEmptyString.isValid("foo\uFEFF"));
    }

    @Test
    void equals_nonEmptyStrings_worksOk() {
        NonEmptyString s1 = NonEmptyString.of("foo");
        NonEmptyString s2 = NonEmptyString.of("foo");
        NonEmptyString s3 = NonEmptyString.of("bar");
        assertEquals(s1, s2);
        assertNotEquals(s1, s3);
    }

    @Test
    void equals_regularStrings_worksOk() {
        NonEmptyString s1 = NonEmptyString.of("foo");
        String s2 = "foo";
        String s3 = "bar";
        assertEquals(s1, s2);
        assertNotEquals(s1, s3);
    }
}
