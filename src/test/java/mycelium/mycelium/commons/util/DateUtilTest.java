package mycelium.mycelium.commons.util;

import static mycelium.mycelium.commons.util.DateUtil.isBeforeToday;
import static mycelium.mycelium.commons.util.DateUtil.isWithinThisAndNextWeek;
import static mycelium.mycelium.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class DateUtilTest {
    @Test
    public void throwsExceptionWhenDateIsNullForWithinTwoWeeks() {
        assertThrows(NullPointerException.class, () -> isWithinThisAndNextWeek(null));
    }

    @Test
    public void throwsExceptionWhenDateIsNullForIsBeforeToday() {
        assertThrows(NullPointerException.class, () -> isBeforeToday(null));
    }

    @Test
    public void isWithinTwoWeeks_success() {
        assertTrue(isWithinThisAndNextWeek(LocalDate.now().plusDays(3)));
    }

    @Test
    public void isWithinTwoWeeks_wrong() {
        assertFalse(isWithinThisAndNextWeek(LocalDate.now().plusDays(50)));
    }

    @Test
    public void isBeforeToday_wrong() {
        assertFalse(isBeforeToday(LocalDate.now().plusDays(2)));
    }

    @Test
    public void isBeforeToday_success() {
        assertTrue(isBeforeToday(LocalDate.now().minusDays(2)));
    }
}
