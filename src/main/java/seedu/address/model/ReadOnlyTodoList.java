package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.InternshipTodo;

/**
 * Unmodifiable view of a todo list
 */
public interface ReadOnlyTodoList {

    /**
     * Returns an unmodifiable view of the todos list.
     * This list will not contain any duplicate todo tasks.
     */
    ObservableList<InternshipTodo> getTodoList();

}
