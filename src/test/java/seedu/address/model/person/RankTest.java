package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rank(null));
    }

    @Test
    public void constructor_invalidRank_throwsIllegalArgumentException() {
        String invalidRank = "";
        assertThrows(IllegalArgumentException.class, () -> new Rank(invalidRank));
    }

    @Test
    public void isValidRank() {
        // null rank
        assertThrows(NullPointerException.class, () -> Rank.isValidRank(null));

        // invalid rank
        assertFalse(Rank.isValidRank("")); // empty string
        assertFalse(Rank.isValidRank(" ")); // spaces only
        assertFalse(Rank.isValidRank("pte")); // not in valid ranks list
        assertFalse(Rank.isValidRank("hello")); // not in valid ranks list

        // valid rank
        assertTrue(Rank.isValidRank("REC"));
        assertTrue(Rank.isValidRank("PTE"));
        assertTrue(Rank.isValidRank("CPL"));
        assertTrue(Rank.isValidRank("3SG"));
        assertTrue(Rank.isValidRank("2LT"));
    }
}
