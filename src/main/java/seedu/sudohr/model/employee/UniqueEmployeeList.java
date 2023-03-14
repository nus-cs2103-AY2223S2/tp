package seedu.sudohr.model.employee;
import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;

/**
 * A list of employees that enforces uniqueness between its elements and does not allow nulls.
 * An employee is considered unique by comparing using {@code Employee#isSameEmployee(Employee)}. As such, adding and updating
 * of employees uses Employee#isSameEmployee(Employee) for equality to ensure that the employee being added or updated is
 * unique in terms of identity in the UniqueEmployeeList. However, the removal of an employee uses Employee#equals(Object)
 * to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Employee#isSameEmployee(Employee)
 */
public class UniqueEmployeeList implements Iterable<Employee> {
    private final ObservableList<Employee> internalList = FXCollections.observableArrayList();
    private final ObservableList<Employee> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Employee toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEmployee);
    }

    /**
     * Returns true if the list contains a different person with the same email as the given argument.
     */
    public boolean sharesEmail(Employee toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::emailClashes);
    }

    /**
     * Returns true if the list contains a different person with the same phone number as the given argument.
     */
    public boolean sharesPhoneNumber(Employee toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::phoneClashes);
    }


    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     * The person must not share the same email or phone number with another.
     */
    public void add(Employee toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEmployeeException();
        }
        if (sharesPhoneNumber(toAdd)) {
            throw new DuplicatePhoneNumberException();
        }
        if (sharesEmail(toAdd)) {
            throw new DuplicateEmailException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedEmployee} must not be the same as another existing employee in the list.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }
        if (!target.isSameEmployee(editedEmployee) && contains(editedEmployee)) {
            throw new DuplicateEmployeeException();
        }

        if (!target.isSameEmployee(editedEmployee) && sharesPhoneNumber(editedEmployee)) {
            throw new DuplicatePhoneNumberException();
        }

        if (!target.isSameEmployee(editedEmployee) && sharesEmail(editedEmployee)) {
            throw new DuplicateEmailException();
        }

        internalList.set(index, editedEmployee);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Employee toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EmployeeNotFoundException();
        }
    }
    public void setEmployees(UniqueEmployeeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    /**
     * Replaces the contents of this list with {@code employees}.
     * {@code employees} must not contain duplicate employees.
     */
    public void setEmployees(List<Employee> employees) {
        requireAllNonNull(employees);
        if (!employeesAreUnique(employees)) {
            throw new DuplicateEmployeeException();
        }
        if (!phoneNumbersAreUnique(employees)) {
            throw new DuplicatePhoneNumberException();
        }
        if (!emailsAreUnique(employees)) {
            throw new DuplicateEmailException();
        }

        internalList.setAll(employees);
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Employee> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
    @Override
    public Iterator<Employee> iterator() {
        return internalList.iterator();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEmployeeList // instanceof handles nulls
                && internalList.equals(((UniqueEmployeeList) other).internalList));
    }
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
    /**
     * Returns true if {@code employees} contains only unique employees.
     */
    private boolean employeesAreUnique(List<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).isSameEmployee(employees.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code employees} contains only unique emails.
     */
    private boolean emailsAreUnique(List<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).emailClashes(employees.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code employees} contains only unique phone numbers.
     */
    private boolean phoneNumbersAreUnique(List<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).phoneClashes(employees.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
