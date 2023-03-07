package seedu.address.model.department;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueDepartmentList implements Iterable<Department>{
    private final List<Department> departments;

    UniqueDepartmentList() {
        this.departments = new ArrayList<>();
    }

    /**
     * Returns true if the list contains an equivalent department as the given argument.
     */
    public boolean contains(Department toCheck) {
        requireNonNull(toCheck);
        return departments.stream().anyMatch(toCheck::equals);
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
        departments.add(toAdd);
    }

    /**
     * Replaces the department {@code target} in the list with {@code editedDepartment}.
     * {@code target} must exist in the list.
     * The department identity of {@code editedDepartment} must not be the same as another existing department in the list.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireAllNonNull(target, editedDepartment);

        int index = departments.indexOf(target);
        if (index == -1) {
            // throw new DepartmentNotFoundException();
        }

        if (!target.equals(editedDepartment) && contains(editedDepartment)) {
            // throw new DuplicateDepartmentException();
        }

        departments.set(index, editedDepartment);
    }

    /**
     * Removes the equivalent department from the list.
     * The department must exist in the list.
     */
    public void remove(Department toRemove) {
        requireNonNull(toRemove);
        if (!departments.remove(toRemove)) {
            // throw new DepartmentNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code d}.
     * {@code d} must not contain duplicate departments.
     */
    public void setDepartments(List<Department> d) {
        requireAllNonNull(d);
        if (!departmentsAreUnique(d)) {
            // throw new DuplicateDepartmentException();
        }

        // Replace department with new set of departments
        // d.setAll(d);
    }

    @Override
    public Iterator<Department> iterator() {
        return departments.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDepartmentList // instanceof handles nulls
                && departments.equals(((UniqueDepartmentList) other).departments));
    }

    /**
     * Returns true if {@code d} contains only unique departments.
     */
    private boolean departmentsAreUnique(List<Department> d) {
        if (d == null) {
            return true; // Maybe replace with assertion?
        }

        for (int i = 0; i < d.size() - 1; i++) {
            for (int j = i + 1; j < d.size(); j++) {
                if (d.get(i).equals(d.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
