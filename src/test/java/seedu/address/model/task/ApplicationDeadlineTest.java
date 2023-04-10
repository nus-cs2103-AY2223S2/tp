package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipTodoBuilder;

/**
 * Contains tests for {@code ApplicationDeadline}.
 */
public class ApplicationDeadlineTest {
    private final InternshipTodo todoTester = new InternshipTodoBuilder().build();
    private final ApplicationDeadline ad = todoTester.getDeadline();

    @Test
    public void isValidDate_returnsTrue() {
        assertTrue(ApplicationDeadline.isValidDate(
                LocalDate.of(2100, 3, 4)));
    }

    @Test
    public void getDeadline_getSuccessful() {
        assertEquals(ad.getDeadline(), ad.applicationDeadline);
    }

    @Test
    public void hashCode_sameHashTrue() {
        assertEquals(ad.hashCode(), ad.applicationDeadline.hashCode());
    }

    @Test
    public void equals() {
        ApplicationDeadline notAd = new ApplicationDeadline(LocalDate.of(2024, 6, 4));
        // same object -> returns true
        assertTrue(ad.equals(ad));

        assertFalse(notAd.equals(ad));
    }
}
