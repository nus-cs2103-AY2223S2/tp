package seedu.address.model.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeliverySlotTest {

    @Test
    void testCatchInvalidSlot() {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DeliverySlot("-1");
        });

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DeliverySlot("a");
        });

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new DeliverySlot("");
        });

        assertThrowsExactly(NullPointerException.class, () -> {
            new DeliverySlot(null);
        });
    }

    @Test
    void testIsValidRange() {
        assertTrue(new DeliverySlot("1").isValidRange());
        assertFalse(new DeliverySlot("6").isValidRange());
        assertFalse(new DeliverySlot("0").isValidRange());
    }

    @Test
    void testGetDescription() {
        assertEquals("N.A.", new DeliverySlot("0").getDescription());
        assertEquals("Extra hours (4PM++)", new DeliverySlot("6").getDescription());
    }
}
