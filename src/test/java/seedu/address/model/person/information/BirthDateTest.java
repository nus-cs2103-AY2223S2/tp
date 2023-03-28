package seedu.address.model.person.information;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;

class BirthDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BirthDate(null));
    }

    @Test
    public void constructor_invalidBirthDate_throwsIllegalArgumentException() {
        String invalidBirthDate = "random";
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(invalidBirthDate));
    }

    @Test
    public void isValidBirthDate() {
        // null address
        assertThrows(NullPointerException.class, () -> BirthDate.isValidBirthDate(null));

        // invalid birthdate
        assertFalse(BirthDate.isValidBirthDate("")); // empty string
        assertFalse(BirthDate.isValidBirthDate(" ")); // spaces only
        assertFalse(BirthDate.isValidBirthDate("hello")); // random letters
        assertFalse(BirthDate.isValidBirthDate("23-03-2020")); // not correct format

        // valid birthdate
        assertTrue(BirthDate.isValidBirthDate("2023-03-23"));
        assertTrue(BirthDate.isValidBirthDate("2000-12-28"));
    }

    @Test
    public void getAge() {
        BirthDate bod1 = new BirthDate("2000-01-23");
        BirthDate bod2 = new BirthDate("1945-03-06");

        LocalDate currDate = LocalDate.now();

        assertEquals(Period.between(bod1.getBirthDate(), currDate)
                .getYears(), bod1.getAge());
        assertEquals(Period.between(bod2.getBirthDate(), currDate)
                .getYears(), bod2.getAge());
    }

}
