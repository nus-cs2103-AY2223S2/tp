package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CreatedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreatedDate(null));
    }

    @Test
    public void constructor_invalidCreatedDate_throwsIllegalArgumentException() {
        LocalDate invalidCreatedDate = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> new CreatedDate(invalidCreatedDate));
    }

    @Test
    public void isValidCreatedDate() {
        // null date
        assertThrows(NullPointerException.class, () -> CreatedDate.isValidCreatedDate(null));

        LocalDate now = LocalDate.now();

        // invalid date
        assertFalse(CreatedDate.isValidCreatedDate(now.plusDays(1))); // tomorrow
        assertFalse(CreatedDate.isValidCreatedDate(now.plusYears(1))); // one year later

        // valid date
        assertTrue(CreatedDate.isValidCreatedDate(now)); // today
        assertTrue(CreatedDate.isValidCreatedDate(now.minusDays(1))); // one day ago
        assertTrue(CreatedDate.isValidCreatedDate(now.minusYears(1))); // one year ago
    }
}
