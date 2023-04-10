package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTodoException;
import seedu.address.model.task.exceptions.TodoNotFoundException;
import seedu.address.testutil.InternshipTodoBuilder;

/**
 * Contains tests for {@code UniqueTodoList}.
 */
public class UniqueTodoListTest {

    private final UniqueTodoList uniqueTodoList = new UniqueTodoList();

    @Test
    public void addTodo_duplicateTodos_alertDuplicates() {
        uniqueTodoList.addTodo(new InternshipTodoBuilder().build());
        assertThrows(DuplicateTodoException.class, () -> {
            uniqueTodoList.addTodo(new InternshipTodoBuilder().build());
        });
    }

    @Test
    public void setTodo_duplicateTodos_alertDuplicates() {
        uniqueTodoList.addTodo(new InternshipTodoBuilder().withJobTitle("test 1").build());
        uniqueTodoList.addTodo(new InternshipTodoBuilder().withJobTitle("test 2").build());
        assertThrows(DuplicateTodoException.class, () -> {
            uniqueTodoList.setTodo(new InternshipTodoBuilder().withJobTitle("test 2").build(),
                    new InternshipTodoBuilder().withJobTitle("test 1").build());
        });
    }

    @Test
    public void setTodo_todoExists_alertNotFound() {
        uniqueTodoList.addTodo(new InternshipTodoBuilder().withJobTitle("test 1").build());
        assertThrows(TodoNotFoundException.class, () -> {
            uniqueTodoList.setTodo(new InternshipTodoBuilder().withJobTitle("test 2").build(),
                    new InternshipTodoBuilder().withJobTitle("test 1").build());
        });
    }

    @Test
    public void setTodo_todoList_setSuccessful() {
        uniqueTodoList.addTodo(new InternshipTodoBuilder().withJobTitle("test 1").build());
        UniqueTodoList replacement = new UniqueTodoList();
        replacement.addTodo(new InternshipTodoBuilder().withJobTitle("repl 1").build());
        replacement.addTodo(new InternshipTodoBuilder().withJobTitle("repl 2").build());
        uniqueTodoList.setTodo(replacement);
        assertEquals(uniqueTodoList, replacement);
    }

    @Test
    public void remove_todoNotExists_alertNotFound() {
        uniqueTodoList.addTodo(new InternshipTodoBuilder().withJobTitle("test 1").build());
        assertThrows(TodoNotFoundException.class, () -> {
            uniqueTodoList.remove(new InternshipTodoBuilder().withJobTitle("test 2").build());
        });
    }

    @Test
    public void equals_uniqueTodoList_returnsTrue() {
        UniqueTodoList replacement = new UniqueTodoList();
        assertTrue(replacement.equals(uniqueTodoList));
    }
}
