package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.SampleDateTimeUtil.ONE_PM_START_DATETIME;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_PM_END_DATETIME;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_END_DATETIME;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_END_DATETIME_2;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_START_DATETIME;
import static seedu.address.testutil.SampleDateTimeUtil.VALID_START_DATETIME_2;

import org.junit.jupiter.api.Test;

class IsolatedEventTest {

    @Test
    void occurBetween_test() {
        IsolatedEvent event = new IsolatedEvent("biking", VALID_START_DATETIME, VALID_END_DATETIME);

        assertTrue(event.occursBetween(VALID_START_DATETIME, VALID_END_DATETIME));
        assertTrue(event.occursBetween(ONE_PM_START_DATETIME, THREE_PM_END_DATETIME));

    }

    @Test
    void notOccurBetween_test() {
        IsolatedEvent event = new IsolatedEvent("biking", VALID_START_DATETIME, VALID_END_DATETIME);
        assertFalse(event.occursBetween(VALID_START_DATETIME_2, VALID_END_DATETIME_2));
    }

    @Test
    void toString_test() {
        IsolatedEvent event = new IsolatedEvent("biking", VALID_START_DATETIME, VALID_END_DATETIME);
        assertEquals("biking from: 09/03/2023 14:00 to: 09/03/2023 15:00", event.toString());
    }

}
