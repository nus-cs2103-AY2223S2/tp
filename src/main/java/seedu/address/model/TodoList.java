package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.UniqueTodoList;

/**
 * Wraps all data at the todo-list level
 * Duplicates are not allowed (by .isSameTodo comparison)
 */
public class TodoList implements ReadOnlyTodoList {

    private final UniqueTodoList todos;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * TodoList that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        todos = new UniqueTodoList();
    }

    public TodoList() {}

    /**
     * Creates an TodoList using the InternshipTodos in the {@code toBeCopied}
     */
    public TodoList(ReadOnlyTodoList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the todo list with {@code todos}.
     * {@code todos} must not contain duplicate todos.
     */
    public void setTodo(List<InternshipTodo> todos) {
        this.todos.setTodo(todos);
    }

    /**
     * Replaces the given todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedTodo} must not be the same as another existing todo in the tracker.
     */
    public void setTodo(InternshipTodo target,
                        InternshipTodo editedTodo) {
        requireNonNull(editedTodo);

        todos.setTodo(target, editedTodo);
    }

    /**
     * Resets the existing data of this {@code TodoList} with {@code newData}.
     */
    public void resetData(ReadOnlyTodoList newData) {
        requireNonNull(newData);

        setTodo(newData.getTodoList());
    }

    /**
     * Returns true if a todo with the same identity
     * as {@code todo} exists in the tracker.
     */
    public boolean hasTodo(InternshipTodo todo) {
        requireNonNull(todo);
        return todos.containsTodo(todo);
    }

    /**
     * Adds a todo to the tracker.
     * The todo must not already exist in the tracker.
     */
    public void addTodo(InternshipTodo todo) {
        todos.addTodo(todo);
    }

    /**
     * Removes {@code key} from this {@code TodoList}.
     * {@code key} must exist in the todo list.
     */
    public void removeTodo(InternshipTodo key) {
        todos.remove(key);
    }

    /**
     * Clear todo list.
     */
    public void clearTodo(ReadOnlyTodoList newData) {
        setTodo(newData.getTodoList());
    }

    //// util methods

    @Override
    public String toString() {
        return todos.asUnmodifiableObservableList().size() + " todos";
    }

    @Override
    public ObservableList<InternshipTodo> getTodoList() {
        return todos.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoList // instanceof handles nulls
                && todos.equals(((TodoList) other).todos));
    }

    @Override
    public int hashCode() {
        return todos.hashCode();
    }
}
