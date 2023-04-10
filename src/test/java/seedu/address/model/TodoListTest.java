package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipTodoBuilder;

/**
 * Contains tests for {@code TodoList}.
 */
public class TodoListTest {

    @Test
    public void equals_returnsTrue() {
        TodoList todoList = new TodoList();
        todoList.addTodo(new InternshipTodoBuilder().build());
        TodoList other = new TodoList();
        other.addTodo(new InternshipTodoBuilder().build());
        assertTrue(todoList.equals(other));
    }
}
