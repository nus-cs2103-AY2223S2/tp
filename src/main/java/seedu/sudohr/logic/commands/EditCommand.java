package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.sudohr.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.commons.util.CollectionUtil;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.employee.Address;
import seedu.sudohr.model.employee.Email;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.Name;
import seedu.sudohr.model.employee.Phone;
import seedu.sudohr.model.tag.Tag;

/**
 * Edits the details of an existing employee inside SudoHR.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed employee list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited Employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in SudoHR.";
    // TODO DUPLICATED IDENTITY FIELDS

    private final Index index;
    private final EditEmployeeDescriptor editEmployeeDescriptor;

    /**
     * @param index of the employee in the filtered employee list to edit
     * @param editEmployeeDescriptor details to edit the employee with
     */
    public EditCommand(Index index, EditEmployeeDescriptor editEmployeeDescriptor) {
        requireNonNull(index);
        requireNonNull(editEmployeeDescriptor);

        this.index = index;
        this.editEmployeeDescriptor = new EditEmployeeDescriptor(editEmployeeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToEdit = lastShownList.get(index.getZeroBased());
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
    private static Employee createEditedEmployee(Employee employeeToEdit,
                                                 EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        Id updatedId = editEmployeeDescriptor.getId().orElse(employeeToEdit.getId());
        Name updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Email updatedEmail = editEmployeeDescriptor.getEmail().orElse(employeeToEdit.getEmail());
        Address updatedAddress = editEmployeeDescriptor.getAddress().orElse(employeeToEdit.getAddress());
        Set<Tag> updatedTags = editEmployeeDescriptor.getTags().orElse(employeeToEdit.getTags());

        return new Employee(updatedId, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
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
        return index.equals(e.index)
                && editEmployeeDescriptor.equals(e.editEmployeeDescriptor);
    }

    /**
     * Stores the details to edit the employee with. Each non-empty field value will replace the
     * corresponding field value of the employee.
     */
    public static class EditEmployeeDescriptor {
        private Id id;
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setId(toCopy.id);
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(id, name, phone, email, address, tags);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
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

            return getId().equals(e.getId())
                    && getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
