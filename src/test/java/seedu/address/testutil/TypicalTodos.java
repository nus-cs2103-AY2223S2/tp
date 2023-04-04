package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TodoList;
import seedu.address.model.task.InternshipTodo;

/**
 * A utility class containing a list of {@code InternshipTodo} objects to be used in tests.
 */
public class TypicalTodos {
    public static final InternshipTodo META_T = new InternshipTodoBuilder().withTitle("Meta")
            .withJobTitle("Software Tester").withDeadline("2024-05-06").build();
    public static final InternshipTodo BANK_OF_AMERICA_T = new InternshipTodoBuilder().withTitle("Bank of America")
            .withJobTitle("Software Engineer").withDeadline("2023-05-06").build();
    public static final InternshipTodo GOOGLE_T = new InternshipTodoBuilder().withTitle("Google")
            .withJobTitle("Product Manager").withDeadline("2023-08-15").withNote("aim this").build();
    public static final InternshipTodo NETFLIX_T = new InternshipTodoBuilder().withTitle("Netflix")
            .withJobTitle("Network Engineer").withDeadline("2024-01-31").build();
    public static final InternshipTodo ORACLE_T = new InternshipTodoBuilder().withTitle("Oracle")
            .withJobTitle("Data Engineer").withDeadline("2023-12-06").withNote("3 tech interviews").build();

    /**
     * Returns a {@code TodoList} with all the typical todos.
     */
    public static TodoList getTypicalTodoList() {
        TodoList tl = new TodoList();
        for (InternshipTodo todo : getTodos()) {
            tl.addTodo(todo);
        }
        return tl;
    }

    public static List<InternshipTodo> getTodos() {
        return new ArrayList<>(Arrays.asList(META_T, BANK_OF_AMERICA_T, GOOGLE_T, NETFLIX_T, ORACLE_T));
    }
}
