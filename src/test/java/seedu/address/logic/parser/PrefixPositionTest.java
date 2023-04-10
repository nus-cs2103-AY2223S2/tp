package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrefixPositionTest {
    private static final PrefixPosition PREFIX_POSITION =
            new PrefixPosition(Prefix.NAME, 5);

    @Test
    public void toString_sameString_equals() {
        PrefixPosition otherPrefixPosition =
                new PrefixPosition(Prefix.NAME, 5);
        assertEquals(PREFIX_POSITION.toString(), otherPrefixPosition.toString());
    }

    @Test
    public void compareTo() {
        PrefixPosition lowerPrefixPosition =
                new PrefixPosition(Prefix.NAME, 4);
        PrefixPosition samePrefixPosition =
                new PrefixPosition(Prefix.NAME, 5);
        assertEquals(1, PREFIX_POSITION.compareTo(lowerPrefixPosition));
        assertEquals(-1, lowerPrefixPosition.compareTo(PREFIX_POSITION));
        assertEquals(0, samePrefixPosition.compareTo(PREFIX_POSITION));
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(PREFIX_POSITION, PREFIX_POSITION);
    }

    @Test
    public void equals_sameFields_true() {
        PrefixPosition samePrefixPosition =
                new PrefixPosition(Prefix.NAME, 5);
        assertEquals(PREFIX_POSITION, samePrefixPosition);
    }
}
