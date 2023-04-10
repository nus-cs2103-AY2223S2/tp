package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipTodoBuilder;

/**
 * Contains tests for {@code ApplicationDeadline}.
 */
public class ApplicationDeadlineTest {
    private final InternshipTodo todoTester = new InternshipTodoBuilder().build();
    private final ApplicationDeadline ad = todoTester.getDeadline();

    @Test
    public void getDeadline_getSuccessful() {
        assertEquals(ad.getDeadline(), ad.applicationDeadline);
    }

    @Test
    public void hashCode_sameHashTrue() {
        assertEquals(ad.hashCode(), ad.applicationDeadline.hashCode());
    }
}
