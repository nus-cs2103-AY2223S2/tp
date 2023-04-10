package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipTodoBuilder;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for {@code InternshipTodo}.
 */
public class InternshipTodoTest {
    private final InternshipTodo todoTester = new InternshipTodoBuilder().build();

    @Test
    public void newTodo_nullNote_createSuccessful() {
        InternshipTodo basicTodoTester = new InternshipTodoBuilder().basicBuild();
        InternshipTodo todoBuild = new InternshipTodo(basicTodoTester.getInternshipTitle(),
                basicTodoTester.getJobTitle(), basicTodoTester.getDeadline(), basicTodoTester.getDate(),
                basicTodoTester.getType());
        assertEquals(basicTodoTester, todoBuild);
    }

    @Test
    public void getJsonDeadline_getSuccessful() {
        assertEquals(todoTester.getJsonDeadline(), "2025-03-04");
    }

    @Test
    public void getJsonDate_getSuccessful() {
        assertEquals(todoTester.getJsonDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
    }

    @Test
    public void getDateString_getSuccessful() {
        assertEquals(todoTester.getDateString(),
                DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(LocalDate.now()));
    }

    @Test
    public void getTodoHash_getSuccessful() {
        assertEquals(todoTester.hashCode(), Objects.hash(todoTester.getInternshipTitle(), todoTester.getJobTitle(),
                todoTester.getDeadline(), todoTester.getNote(), todoTester.getDate()));
    }

    @Test
    public void equals_testNote_alertDifferentClass() {
        assertFalse(todoTester.equals(new NoteBuilder().build()));
    }
}
