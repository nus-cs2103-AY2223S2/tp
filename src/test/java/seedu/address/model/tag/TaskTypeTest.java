package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * A test class to test the input validity in the enum class {@code TaskType}.
 */
public class TaskTypeTest {

    @Test
    public void validTodoType_evaluateToTrue() {
        assertTrue(TaskType.isValidTodo("TODO"));
    }

    @Test
    public void invalidTodoType_evaluateToFalse() {
        assertFalse(TaskType.isValidTodo("any"));
    }

    @Test
    public void validNoteType_evaluateToTrue() {
        assertTrue(TaskType.isValidNote("NOTE"));
    }

    @Test
    public void invalidNoteType_evaluateToFalse() {
        assertFalse(TaskType.isValidNote("keep blank"));
    }
}
