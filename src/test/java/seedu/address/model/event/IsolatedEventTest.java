package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.SampleDateTimeUtil.FOUR_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.ONE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.SIX_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;

import org.junit.jupiter.api.Test;

class IsolatedEventTest {

    @Test
    void occurBetween_test() {
        IsolatedEvent event = new IsolatedEvent("biking", TWO_O_CLOCK_VALID, THREE_O_CLOCK_VALID);

        assertTrue(event.occursBetween(TWO_O_CLOCK_VALID, THREE_O_CLOCK_VALID));
        assertTrue(event.occursBetween(ONE_O_CLOCK_VALID, THREE_O_CLOCK_VALID));

    }

    @Test
    void notOccurBetween_test() {
        IsolatedEvent event = new IsolatedEvent("biking", TWO_O_CLOCK_VALID, THREE_O_CLOCK_VALID);
        assertFalse(event.occursBetween(FOUR_O_CLOCK_VALID, SIX_O_CLOCK_VALID));
    }

    @Test
    void toString_test() {
        IsolatedEvent event = new IsolatedEvent("biking", TWO_O_CLOCK_VALID, THREE_O_CLOCK_VALID);
        assertEquals("biking from: 09/03/2024 14:00 to: 09/03/2024 15:00", event.toString());
    }

}
