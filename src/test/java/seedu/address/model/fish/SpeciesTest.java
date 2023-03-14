package seedu.address.model.fish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpeciesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Species(null));
    }

    @Test
    public void constructor_invalidSpecies_throwsIllegalArgumentException() {
        String invalidSpecies = "";
        assertThrows(IllegalArgumentException.class, () -> new Species(invalidSpecies));
    }

    @Test
    public void isValidSpecies() {
        // null date
        assertThrows(NullPointerException.class, () -> Species.isValidSpecies(null));

        // invalid species
        assertFalse(Species.isValidSpecies("")); // empty string

        // valid species
        assertTrue(Species.isValidSpecies("Guppy")); 
        assertTrue(Species.isValidSpecies("Tetra"));
        assertTrue(Species.isValidSpecies("Betta"));
    }
}
