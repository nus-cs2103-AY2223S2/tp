package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class FacultyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Faculty(null));
    }

    @Test
    public void constructor_invalidFaculty_throwsIllegalArgumentException() {
        String invalidFaculty = "123";
        assertThrows(IllegalArgumentException.class, () -> new Faculty(invalidFaculty));
    }

    @Test
    public void isValidFaculty() {
        // null faculty
        assertThrows(NullPointerException.class, () -> Faculty.isValidFaculty(null));

        // invalid faculty
        assertFalse(Major.isValidMajor("12345")); // only numbers in faculty
        assertFalse(Major.isValidMajor("SchoolOf23Computing")); // numbers in faculty
        assertFalse(Major.isValidMajor("School_Of_Computing")); // underscore in faculty
        assertFalse(Major.isValidMajor("School @ Computing")); // '@' in faculty
        assertFalse(Major.isValidMajor("School-of-Computing")); // hyphen in faculty
        assertFalse(Major.isValidMajor("#SOC")); // hashtag in faculty
        assertFalse(Major.isValidMajor("SOC!")); // exclamation mark in faculty
        assertFalse(Major.isValidMajor("School^of^computing")); // '^' in faculty

        // valid faculty
        assertTrue(Major.isValidMajor("School Of Computing")); // normal spelling faculty
        assertTrue(Major.isValidMajor("school of computing")); // lowercase faculty
        assertTrue(Major.isValidMajor("SCHOOL OF COMPUTING")); // uppercase faculty
        assertTrue(Major.isValidMajor("ScHoOL Of COmpuTING")); // mixed case faculty
        assertTrue(Major.isValidMajor("soc")); // single-word faculty
    }
}
