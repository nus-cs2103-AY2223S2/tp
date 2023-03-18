package seedu.address.model.person.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RegionTest {

    @Test
    public void constructor_invalidRegion_throwsIllegalArgumentException() {
        String invalidRegion = "";
        assertThrows(IllegalArgumentException.class, () -> new Region(invalidRegion));
    }

    @Test
    public void isValidRegion() {
        // null address
        assertFalse(Region.isValidRegion(null));

        // invalid region
        assertFalse(Region.isValidRegion("")); // empty string
        assertFalse(Region.isValidRegion(" ")); // spaces only
        assertFalse(Region.isValidRegion("hello")); // random letters

        // valid region
        assertTrue(Region.isValidRegion("north"));
        assertTrue(Region.isValidRegion("NORTHEAST"));
        assertTrue(Region.isValidRegion("CenTraL"));
    }

}
