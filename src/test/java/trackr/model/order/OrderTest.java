package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderDeadline(null));
    }

    @Test
    public void constructor_wrongFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OrderDeadline("2023/01/01"));
    }

    @Test
    public void constructor_dateNotInCalendar_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OrderDeadline("35/14/2023"));
    }

    @Test
    public void constructor_notDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OrderDeadline("Not a Date"));
    }

    @Test
    public void isValidOrderDeadline() {
        // null order deadline
        assertThrows(NullPointerException.class, () -> OrderDeadline.isValidOrderDeadline(null));

        // invalid order deadline

        String wrongFormatDate = "2024-01-01";
        assertFalse(OrderDeadline.isValidOrderDeadline(wrongFormatDate)); //deadline is in the wrong format

        String invalidDate = "35/14/2024";
        assertFalse(OrderDeadline.isValidOrderDeadline(invalidDate)); //deadline is an invalid date in the calendar

        String notADate = "Not a Date";
        assertFalse(OrderDeadline.isValidOrderDeadline(notADate)); // deadline is not a date

        // valid order deadline
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);
        String tomorrow = LocalDate.now().plusDays(1).format(dtf);
        String futureDate = LocalDate.now().plusMonths(7).plusDays(21).format(dtf);
        String pastDate = LocalDate.now().minusMonths(7).minusDays(10).format(dtf);
        assertTrue(OrderDeadline.isValidOrderDeadline(todayDate)); // today's date
        assertTrue(OrderDeadline.isValidOrderDeadline(tomorrow)); // tomorrow's date
        assertTrue(OrderDeadline.isValidOrderDeadline(futureDate)); // future date
        assertTrue(OrderDeadline.isValidOrderDeadline(pastDate)); // past date
    }

    @Test
    public void toStringTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String expectedDate = LocalDate.now().format(dtf2);

        assertEquals(expectedDate, new OrderDeadline(todayDate).toString());
    }

    @Test
    public void toJsonString() {
        String expectedDate = "10/10/2023";
        assertEquals(expectedDate, new OrderDeadline(expectedDate).toJsonString());
    }

    @Test
    public void equals() {
        OrderDeadline orderDeadline = new OrderDeadline("01/01/2024");
        OrderDeadline differentDeadline = new OrderDeadline("15/07/2025");

        assertTrue(orderDeadline.equals(orderDeadline)); //same object
        assertTrue(orderDeadline.equals(new OrderDeadline("01/01/2024"))); //same deadline

        assertFalse(orderDeadline.equals(null)); //null
        assertFalse(orderDeadline.equals(differentDeadline)); //different deadlines
        assertFalse(orderDeadline.equals(1)); //different type
    }

}
