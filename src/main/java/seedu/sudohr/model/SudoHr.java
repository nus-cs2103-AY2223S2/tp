package seedu.sudohr.model;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.department.UniqueDepartmentList;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.UniqueLeaveList;
import seedu.sudohr.model.person.Person;
import seedu.sudohr.model.person.UniquePersonList;

/**
 * Wraps all data at the sudohr-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SudoHr implements ReadOnlySudoHr {

    private final UniquePersonList persons;
    private final UniqueDepartmentList departments;
    private final UniqueLeaveList leaves;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        leaves = new UniqueLeaveList();
        persons = new UniquePersonList();
        departments = new UniqueDepartmentList();
    }

    public SudoHr() {
    }

    /**
     * Creates an SudoHr using the Persons in the {@code toBeCopied}
     */
    public SudoHr(ReadOnlySudoHr toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the department list with {@code departments}.
     * {@code departments} must not contain duplicate departments.
     */
    public void setDepartments(List<Department> departments) {
        this.departments.setDepartments(departments);
    }

    /**
     * Replaces the contents of the leaves list with {@code departments}.
     * {@code departments} must not contain duplicate departments.
     */
    public void setLeaves(List<Leave> leaves) {
        this.leaves.setLeaves(leaves);
    }

    /**
     * Resets the existing data of this {@code SudoHr} with {@code newData}.
     */
    public void resetData(ReadOnlySudoHr newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setDepartments(newData.getDepartmentList());
        setLeaves(newData.getLeavesList());
    }

    // =========== Person-Level Operations
    // ==============================================================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the sudohr book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the sudohr book.
     * The person must not already exist in the sudohr book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the sudohr book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the sudohr book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code SudoHr}.
     * {@code key} must exist in the sudohr book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    // =========== Department-Level Operations
    // ==========================================================================

    /**
     * Returns true if a department with the same identity as {@code department}
     * exists in SudoHR.
     */
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return departments.contains(department);
    }

    /**
     * Returns the department with the given name.
     *
     * @param name The department name to find.
     * @return The corresponding department.
     */
    public Department getDepartment(DepartmentName name) {
        return departments.getDepartment(name);
    }

    /**
     * Adds a department to the address book.
     * The department must not already exist in the address book.
     */
    public void addDepartment(Department d) {
        departments.add(d);
    }

    /**
     * Replaces the given department {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in
     * the address book.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireNonNull(editedDepartment);
        departments.setDepartment(target, editedDepartment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDepartment(Department key) {
        departments.remove(key);
    }

    /**
     * Adds a given employee from a given department
     *
     * @param p The employee to add
     * @param d The department to add the employee to
     */
    public void addEmployeeToDepartment(Person p, Department d) {
        requireNonNull(p);
        requireNonNull(d);

        d.addEmployee(p);
    }

    /**
     * Removes a given employee from a given department
     *
     * @param p The employee to remove
     * @param d The department to remove the employee fro
     */
    public void removeEmployeeFromDepartment(Person p, Department d) {
        requireNonNull(p);
        requireNonNull(d);

        d.removeEmployee(p);
    }

    //// leave-level operations

    /**
     * Adds a leave to the address book.
     * The leave must not already exist in the address book.
     */
    public void addLeave(Leave leave) {
        leaves.addLeave(leave);
    }

    /**
     * Removes {@code leave} from this {@code SudoHr}.
     * {@code leave} must exist in the sudohr book.
     */
    public void deleteLeave(Leave leave) {
        leaves.remove(leave);
    }

    /**
     * Returns true if a leave with the same identity as {@code leave} exists in
     * the sudohr book.
     */
    public boolean hasLeave(Leave leave) {
        requireNonNull(leave);
        return leaves.contains(leave);
    }

    public Leave getLeave(Leave leaveToAdd) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.isSameLeave(leaveToAdd)) {
                return leave;
            }
        }
        return leaveToAdd;
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the specified leave
     * {@code date}
     * the sudohr book.
     */
    public boolean hasEmployeeOnLeave(Date date, Person person) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.getTitle().equals(date) & leave.hasPerson(person)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a person {@code person} to a specified leave{@code leave}
     * in the sudohr book.
     */
    public void addEmployeeToLeave(Leave leave, Person person) {
        requireAllNonNull(leave, person);
        leave.addPerson(person);
    }

    /**
     * Deletes a person {@code person} from a specified leaeve{@code leave}
     * in the sudohr book.
     */
    public void deleteEmployeeFromLeave(Leave leave, Person person) {
        requireAllNonNull(leave, person);
        leave.deletePerson(person);
    }

    /**
     * Update a person {@code person} with editedPerson {@code person} in all leaves
     * in the sudohr book.
     */
    public void cascadeUpdateUserInLeaves(Person personToEdit, Person editedPerson) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.hasPerson(personToEdit)) {
                leave.deletePerson(personToEdit);
                leave.addPerson(editedPerson);
            }
        }
    }

    /**
     * Deletes a person {@code person} from all leaves
     * in the sudohr book.
     */
    public void cascadeDeleteUserInLeaves(Person personToDelete) {
        ObservableList<Leave> leaveList = this.getLeavesList();
        for (Leave leave : leaveList) {
            if (leave.hasPerson(personToDelete)) {
                leave.deletePerson(personToDelete);
            }
        }
    }

    /**
     * return the list of all leaves in sudohr
     */
    @Override
    public ObservableList<Leave> getLeavesList() {
        return leaves.asUnmodifiableObservableList();
    }

    //// leave-level operations

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Department> getDepartmentList() {
        return departments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SudoHr // instanceof handles nulls
                        && persons.equals(((SudoHr) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
