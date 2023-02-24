package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.DuplicateHomeworkException;
import seedu.address.model.student.exceptions.HomeworkNotFoundException;

/**
 * A list of homework that enforces uniqueness between its elements and does not allow nulls.
 * A homework is considered unique by comparing using {@code Homework#isSameHomework(Homework)}.
 * As such, adding and updating of homework uses Homework#isSameHomework(Homework) for equality
 * so as to ensure that the homework being added or updated is unique in terms of identity in the
 * UniqueHomeworkList. However, the removal of a homework uses Homework#equals(Object) so as to ensure
 * that the homework with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Homework#isSameHomework(Homework)
 */
public class UniqueHomeworkList implements Iterable<Homework> {

    private final ObservableList<Homework> internalList = FXCollections.observableArrayList();
    private final ObservableList<Homework> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent homework as the given argument.
     *
     * @param toCheck the homework to be checked
     * @return true if the list contains the homework
     */
    public boolean contains(Homework toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameHomework);
    }

    /**
     * Adds a homework to the list.
     * The homework must not already exist in the list.
     *
     * @param toAdd the homework to be added
     */
    public void add(Homework toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateHomeworkException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the homework {@code target} in the list with {@code editedHomework}.
     * {@code target} must exist in the list.
     * The homework identity of {@code editedHomework} must not be the same as another existing homework in the list.
     */
    public void setHomework(Homework target, Homework editedHomework) {
        requireAllNonNull(target, editedHomework);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new HomeworkNotFoundException();
        }

        if (!target.isSameHomework(editedHomework) && contains(editedHomework)) {
            throw new DuplicateHomeworkException();
        }

        internalList.set(index, editedHomework);
    }

    /**
     * Removes the equivalent homework from the list.
     * The homework must exist in the list.
     *
     * @param toRemove the homework to be removed
     */
    public void remove(Homework toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new HomeworkNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code homeworks}.
     * {@code homeworks} must not contain duplicate homeworks.
     *
     * @param replacement the homeworks to be added to the list
     */
    public void setHomeworks(UniqueHomeworkList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code homeworks}.
     * {@code homeworks} must not contain duplicate homeworks.
     *
     * @param homeworks the homeworks to be added to the list
     */
    public void setHomeworks(List<Homework> homeworks) {
        requireAllNonNull(homeworks);
        if (!homeworksAreUnique(homeworks)) {
            throw new DuplicateHomeworkException();
        }

        internalList.setAll(homeworks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @param index of the homework to be returned
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public Homework getHomework(int index) {
        return internalList.get(index);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Homework> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list as an unmodifiable {@code ObservableList}.
     */
    @Override
    public Iterator<Homework> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns true if {@code homeworks} contains only unique homeworks.
     *
     * @param homeworks the homeworks to be checked
     * @return true if {@code homeworks} contains only unique homeworks
     */
    private boolean homeworksAreUnique(List<Homework> homeworks) {
        for (int i = 0; i < homeworks.size() - 1; i++) {
            for (int j = i + 1; j < homeworks.size(); j++) {
                if (homeworks.get(i).isSameHomework(homeworks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
