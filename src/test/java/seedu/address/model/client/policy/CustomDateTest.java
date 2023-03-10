package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CustomDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new CustomDate(invalidDate));
    }


    @Test
    void isValidDate() {
        String date1 = "01.01.2023";
        String date2 = "01/01/2023";

        assertTrue(CustomDate.isValidDate(date1));
        assertFalse(CustomDate.isValidDate(date2));
    }
}
