package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTodoException;
import seedu.address.model.task.exceptions.TodoNotFoundException;

/**
 * A list of InternshipTodo that enforces uniqueness between its elements and does not allow nulls.
 * An InternshipTodo is considered unique by comparing using {@code InternshipTodo#isSameTodo(InternshipTodo)}.
 * As such, adding and updating of todos uses InternshipTodo#isSameTodo(InternshipTodo) for equality to ensure that the
 * todo being added or updated is unique in terms of identity in the UniqueTodoList. However, the removal of a todo uses
 * InternshipTodo#equals(Object) to ensure that the InternshipTodo with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see InternshipTodo#isSameTodo(InternshipTodo)
 */
public class UniqueTodoList implements Iterable<InternshipTodo> {
    private final ObservableList<InternshipTodo> internalTodoList =
            FXCollections.observableArrayList();
    private final ObservableList<InternshipTodo> internalUnmodifiableTodoList =
            FXCollections.unmodifiableObservableList(internalTodoList);

    /**
     * Returns true if the list contains an equivalent todo as the given argument.
     */
    public boolean containsTodo(InternshipTodo toCheck) {
        requireNonNull(toCheck);
        return internalTodoList.stream().anyMatch(toCheck::isSameTodo);
    }

    /**
     * Adds a todo to the list.
     * The todo must not already exist in the list.
     */
    public void addTodo(InternshipTodo toAdd) {
        requireNonNull(toAdd);
        if (containsTodo(toAdd)) {
            throw new DuplicateTodoException();
        }
        internalTodoList.add(toAdd);
    }

    /**
     * Replaces the todo {@code target} in the list with {@code editedTodo}.
     * {@code target} must exist in the list.
     * The todo identity of {@code editedTodo} must not be the same as another existing todo in the list.
     */
    public void setTodo(InternshipTodo target, InternshipTodo editedTodo) {
        requireAllNonNull(target, editedTodo);

        int index = internalTodoList.indexOf(target);
        if (index == -1) {
            throw new TodoNotFoundException();
        }

        if (!target.isSameTodo(editedTodo) && containsTodo(editedTodo)) {
            throw new DuplicateTodoException();
        }

        internalTodoList.set(index, editedTodo);
    }

    /**
     * Replaces the all the todo in list {@code target} with {@code replacement}.
     * The todos in  {@code replacement} should be unique.
     */
    public void setTodo(UniqueTodoList replacement) {
        requireNonNull(replacement);
        internalTodoList.setAll(replacement.internalTodoList);
    }

    /**
     * Replaces the contents of this list with {@code todo}.
     * {@code todo} must not contain duplicate todo tasks.
     */
    public void setTodo(List<InternshipTodo> todo) {
        requireAllNonNull(todo);
        if (!todoAreUnique(todo)) {
            throw new DuplicateTodoException();
        }

        internalTodoList.setAll(todo);
    }

    /**
     * Removes the equivalent todo from the list.
     * The todo must exist in the list.
     */
    public void remove(InternshipTodo toRemove) {
        requireNonNull(toRemove);
        if (!internalTodoList.remove(toRemove)) {
            throw new TodoNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<InternshipTodo> asUnmodifiableObservableList() {
        return internalUnmodifiableTodoList;
    }

    @Override
    public Iterator<InternshipTodo> iterator() {
        return internalTodoList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTodoList // instanceof handles nulls
                && internalTodoList.equals(((UniqueTodoList) other).internalTodoList));
    }

    @Override
    public int hashCode() {
        return internalTodoList.hashCode();
    }

    /**
     * Returns true if {@code todo} contains only unique todo.
     */
    private boolean todoAreUnique(List<InternshipTodo> todo) {
        for (int i = 0; i < todo.size() - 1; i++) {
            for (int j = i + 1; j < todo.size(); j++) {
                if (todo.get(i).isSameTodo(todo.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
