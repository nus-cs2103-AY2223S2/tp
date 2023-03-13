package seedu.sudohr.model.department;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.sudohr.model.department.exceptions.DepartmentNotFoundException;
import seedu.sudohr.model.department.exceptions.DuplicateDepartmentException;

/**
 * A list of departments that enforces uniqueness between its elements and does not allow nulls.
 * A department is considered unique by comparing using {@code Department#equals(Department)}. As such, adding and
 * updating of departments uses Department#equals(Department) for equality so as to ensure that the department being
 * added or updated is unique in terms of identity in the UniqueDepartmentList. Also, the removal of a department
 * uses Department#equals(Department) so as to ensure that the department with exactly the same name will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueDepartmentList implements Iterable<Department> {
    private final ObservableList<Department> internalList = FXCollections.observableArrayList();
    private final ObservableList<Department> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent department as the given argument.
     */
    public boolean contains(Department toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a department to the list.
     * The department must not already exist in the list.
     */
    public void add(Department toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDepartmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns a department that has the given name
     * @param name the department name
     * @return the corresponding department
     */
    public Department getDepartment(DepartmentName name) {
        for (Department department: internalList) {
            if (department.getName().equals(name)) {
                return department;
            }
        }
        return null;
    }

    /**
     * Replaces the department {@code target} in the list with {@code editedDepartment}.
     * {@code target} must exist in the list.
     * The department identity of {@code editedDepartment} must not be the same as another existing department in the
     * list.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireAllNonNull(target, editedDepartment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DepartmentNotFoundException();
        }

        if (!target.equals(editedDepartment) && contains(editedDepartment)) {
            throw new DuplicateDepartmentException();
        }

        internalList.set(index, editedDepartment);
    }

    /**
     * Removes the equivalent department from the list.
     * The department must exist in the list.
     */
    public void remove(Department toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DepartmentNotFoundException();
        }
    }

    public void setDepartments(UniqueDepartmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code d}.
     * {@code d} must not contain duplicate internalList.
     */
    public void setDepartments(List<Department> d) {
        requireAllNonNull(d);
        if (!internalListAreUnique(d)) {
            throw new DuplicateDepartmentException();
        }

        // Replace department with new set of internalList
        internalList.setAll(d);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Department> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Department> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDepartmentList // instanceof handles nulls
                && internalList.equals(((UniqueDepartmentList) other).internalList));
    }

    /**
     * Returns true if {@code d} contains only unique internalList.
     */
    private boolean internalListAreUnique(List<Department> departments) {
        if (departments == null) {
            return true; // Maybe replace with assertion?
        }

        for (int i = 0; i < departments.size() - 1; i++) {
            for (int j = i + 1; j < departments.size(); j++) {
                if (departments.get(i).equals(departments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
