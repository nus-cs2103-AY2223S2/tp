package seedu.address.model.pet;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidTimeStamp_throwsIllegalArgumentException() {
        LocalDateTime invalidTimeStamp = LocalDateTime.parse("2024-01-01T00:00:00");
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidTimeStamp));
    }

}
