package seedu.address.model.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateTodoException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of InternshipApplications that enforces uniqueness between its elements and does not allow nulls.
 * An InternshipApplication is considered unique by comparing using
 * {@code InternshipApplication#isSameApplication(InternshipApplication)}. As such, adding and updating of
 * persons uses InternshipApplication#isSameApplication(InternshipApplication) for equality
 * so as to ensure that the person being added or updated is unique in terms of identity in the UniquePersonList.
 * However, the removal of a person uses InternshipApplication#equals(Object) so
 * as to ensure that the InternshipApplication with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueTodoList implements Iterable<InternshipTodo> {
    private final ObservableList<InternshipTodo> internalTodoList = FXCollections.observableArrayList();
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
     * Replaces the application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedApplication} must not be the same
     * as another existing application in the list.
     * @param target
     * @param editedTodo
     */
    public void setTodo(InternshipTodo target, InternshipTodo editedTodo) {
        requireAllNonNull(target, editedTodo);

        int index = internalTodoList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameTodo(editedTodo) && containsTodo(editedTodo)) {
            throw new DuplicatePersonException();
        }

        internalTodoList.set(index, editedTodo);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(InternshipTodo toRemove) {
        requireNonNull(toRemove);
        if (!internalTodoList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setTodo(UniqueTodoList replacement) {
        requireNonNull(replacement);
        internalTodoList.setAll(replacement.internalTodoList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     * @param todo
     */
    public void setTodo(List<InternshipTodo> todo) {
        requireAllNonNull(todo);
        if (!todoAreUnique(todo)) {
            throw new DuplicateTodoException();
        }

        internalTodoList.setAll(todo);
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
     * Returns true if {@code persons} contains only unique persons.
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
