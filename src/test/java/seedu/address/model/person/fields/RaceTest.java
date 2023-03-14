package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
class RaceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Race(null));
    }

    @Test
    public void constructor_invalidRace_throwsIllegalArgumentException() {
        String invalidRace = "123";
        assertThrows(IllegalArgumentException.class, () -> new Race(invalidRace));
    }

    @Test
    public void isValidRace() {
        //null race
        assertThrows(NullPointerException.class, () -> Race.isValidRace(null));

        // invalid race
        assertFalse(Race.isValidRace("12345")); // only numbers in race
        assertFalse(Race.isValidRace("Chinese23")); // numbers in race
        assertFalse(Race.isValidRace("Malay_Indian")); // underscore in race
        assertFalse(Race.isValidRace("Chinese @ Chinese")); // '@' in race
        assertFalse(Race.isValidRace("Malay-Malay")); // hyphen in race
        assertFalse(Race.isValidRace("#Indian")); // hashtag in race
        assertFalse(Race.isValidRace("Eurasian!")); // exclamation mark in race
        assertFalse(Race.isValidRace("Eu^Ra^Sian")); // '^' in race

        assertTrue(Race.isValidRace("Chinese")); // normal spelling race
        assertTrue(Race.isValidRace("malay")); // lowercase race
        assertTrue(Major.isValidMajor("INDIAN")); // uppercase race
        assertTrue(Major.isValidMajor("EuRaAsiaN")); // mixed case race
        assertTrue(Major.isValidMajor("Chinese Indian")); //multiple-word race
    }
}
