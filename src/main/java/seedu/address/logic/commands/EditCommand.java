package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_JOINING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYROLL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.LeaveCounter;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Payroll;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.PicturePath;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing employee in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by their employee ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: employee ID "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "[" + PREFIX_PAYROLL + "PAYROLL] "
            + "[" + PREFIX_LEAVE_COUNT + "LEAVE_COUNT] "
            + "[" + PREFIX_DATE_OF_BIRTH + "DATE_OF_BIRTH] "
            + "[" + PREFIX_DATE_OF_JOINING + "DATE_OF_JOINING] "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited Employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the database.";

    private final EmployeeId employeeId;
    private final EditEmployeeDescriptor editEmployeeDescriptor;

    /**
     * @param employeeId of the employee in the filtered employee list to edit
     * @param editEmployeeDescriptor details to edit the employee with
     */
    public EditCommand(EmployeeId employeeId, EditEmployeeDescriptor editEmployeeDescriptor) {
        requireNonNull(employeeId);
        requireNonNull(editEmployeeDescriptor);

        this.employeeId = employeeId;
        this.editEmployeeDescriptor = new EditEmployeeDescriptor(editEmployeeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!EmployeeId.isValidEmployeeId(employeeId.toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Optional<Employee> optionalEmployee = model.getEmployee(employeeId);
        Employee employeeToEdit = optionalEmployee.orElseThrow(() -> new
                CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX));


        Employee editedEmployee = createEditedEmployee(employeeToEdit, editEmployeeDescriptor);

        if (!employeeToEdit.isSameEmployee(editedEmployee) && model.hasEmployee(editedEmployee)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.setEmployee(employeeToEdit, editedEmployee);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        return new CommandResult(String.format(MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee));
    }

    /**
     * Creates and returns a {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    static Employee createEditedEmployee(Employee employeeToEdit,
                                         EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        Name updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Email updatedEmail = editEmployeeDescriptor.getEmail().orElse(employeeToEdit.getEmail());
        Address updatedAddress = editEmployeeDescriptor.getAddress().orElse(employeeToEdit.getAddress());
        Department updatedDepartment = editEmployeeDescriptor.getDepartment().orElse(employeeToEdit.getDepartment());
        Payroll updatedPayroll = editEmployeeDescriptor.getPayroll().orElse(employeeToEdit.getPayroll());
        LeaveCounter updatedLeaveCounter = editEmployeeDescriptor.getLeaveCounter()
                .orElse(employeeToEdit.getLeaveCounter());
        Optional<LocalDate> updatedDateOfBirth = Optional.ofNullable(editEmployeeDescriptor.getDateOfBirth())
                .flatMap(s -> s).or(employeeToEdit::getDateOfBirthOptional);
        if (updatedDateOfBirth.isEmpty()) {
            updatedDateOfBirth = employeeToEdit.getDateOfBirthOptional();
        }
        Optional<LocalDate> updatedDateOfJoining = Optional.ofNullable(editEmployeeDescriptor.getDateOfJoining())
                .flatMap(s -> s).or(employeeToEdit::getDateOfJoiningOptional);
        if (updatedDateOfJoining.isEmpty()) {
            updatedDateOfJoining = employeeToEdit.getDateOfJoiningOptional();
        }
        PicturePath updatedPicturePath = editEmployeeDescriptor.getPicturePath()
                .orElse(employeeToEdit.getPicturePath());
        Set<Tag> updatedTags = editEmployeeDescriptor.getTags().orElse(employeeToEdit.getTags());

        EmployeeId employeeId = employeeToEdit.getEmployeeId();
        return new Employee(updatedName, employeeId, updatedPhone, updatedEmail, updatedAddress, updatedDepartment,
                updatedPayroll, updatedLeaveCounter, updatedDateOfBirth, updatedDateOfJoining, updatedPicturePath,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return employeeId.equals(e.employeeId)
                && editEmployeeDescriptor.equals(e.editEmployeeDescriptor);
    }

    /**
     * Stores the details to edit the employee with. Each non-empty field value will replace the
     * corresponding field value of the employee.
     */
    public static class EditEmployeeDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Department department;
        private Payroll payroll;
        private LeaveCounter leaveCounter;
        private Optional<LocalDate> dateOfBirth;
        private Optional<LocalDate> dateOfJoining;
        private PicturePath picturePath;
        private Set<Tag> tags;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDepartment(toCopy.department);
            setPayroll(toCopy.payroll);
            setLeaveCounter(toCopy.leaveCounter);
            setDateOfBirth(toCopy.dateOfBirth);
            setDateOfJoining(toCopy.dateOfJoining);
            setPicturePath(toCopy.picturePath);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, department, payroll, leaveCounter,
                    dateOfBirth, dateOfJoining, picturePath, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }

        public void setPayroll(Payroll payroll) {
            this.payroll = payroll;
        }

        public Optional<Payroll> getPayroll() {
            return Optional.ofNullable(payroll);
        }

        public void setLeaveCounter(LeaveCounter leaveCounter) {
            this.leaveCounter = leaveCounter;
        }

        public Optional<LeaveCounter> getLeaveCounter() {
            return Optional.ofNullable(leaveCounter);
        }

        public void setDateOfBirth(Optional<LocalDate> dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Optional<LocalDate> getDateOfBirth() {
            return Optional.ofNullable(dateOfBirth).flatMap(s -> s);
        }

        public void setDateOfJoining(Optional<LocalDate> dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }

        public Optional<LocalDate> getDateOfJoining() {
            return Optional.ofNullable(dateOfJoining).flatMap(s -> s);
        }

        public void setPicturePath(PicturePath picturePath) {
            this.picturePath = picturePath;
        }

        public Optional<PicturePath> getPicturePath() {
            return Optional.ofNullable(picturePath);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEmployeeDescriptor)) {
                return false;
            }

            // state check
            EditEmployeeDescriptor e = (EditEmployeeDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getDepartment().equals(e.getDepartment())
                    && getPayroll().equals(e.getPayroll())
                    && getLeaveCounter().equals(e.getLeaveCounter())
                    && getDateOfBirth().equals(e.getDateOfBirth())
                    && getDateOfJoining().equals(e.getDateOfJoining())
                    && getPicturePath().equals(e.getPicturePath())
                    && getTags().equals(e.getTags());
        }
    }
}
