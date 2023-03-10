package seedu.address.model.client.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FrequencyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Frequency(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidFrequency = "";
        assertThrows(IllegalArgumentException.class, () -> new Frequency(invalidFrequency));
    }

    @Test
    void isValidFrequency() {
        String monthly = "monthly";
        String quarterly = "quarterly";
        String yearly = "yearly";
        String invalid = "uregoweijnowig";

        assertTrue(Frequency.isValidFrequency(monthly));
        assertTrue(Frequency.isValidFrequency(quarterly));
        assertTrue(Frequency.isValidFrequency(yearly));
        assertFalse(Frequency.isValidFrequency(invalid));
    }
}

