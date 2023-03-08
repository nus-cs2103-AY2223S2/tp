package seedu.address.model.department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueDepartmentList implements Iterable<Department>{
    private final ObservableList<Department> internalList = FXCollections.observableArrayList();;
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
            //throw new DuplicateDepartmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the department {@code target} in the list with {@code editedDepartment}.
     * {@code target} must exist in the list.
     * The department identity of {@code editedDepartment} must not be the same as another existing department in the list.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireAllNonNull(target, editedDepartment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            // throw new DepartmentNotFoundException();
        }

        if (!target.equals(editedDepartment) && contains(editedDepartment)) {
            // throw new DuplicateDepartmentException();
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
            // throw new DepartmentNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code d}.
     * {@code d} must not contain duplicate internalList.
     */
    public void setDepartments(List<Department> d) {
        requireAllNonNull(d);
        if (!internalListAreUnique(d)) {
            // throw new DuplicateDepartmentException();
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
