package seedu.address.testutil;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.LeaveCounter;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Payroll;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditEmployeeDescriptor objects.
 */
public class EditEmployeeDescriptorBuilder {

    private EditEmployeeDescriptor descriptor;

    public EditEmployeeDescriptorBuilder() {
        descriptor = new EditCommand.EditEmployeeDescriptor();
    }

    public EditEmployeeDescriptorBuilder(EditCommand.EditEmployeeDescriptor descriptor) {
        this.descriptor = new EditCommand.EditEmployeeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEmployeeDescriptor} with fields containing {@code employee}'s details
     */
    public EditEmployeeDescriptorBuilder(Employee employee) {
        descriptor = new EditEmployeeDescriptor();
        descriptor.setName(employee.getName());
        descriptor.setPhone(employee.getPhone());
        descriptor.setEmail(employee.getEmail());
        descriptor.setAddress(employee.getAddress());
        descriptor.setDepartment(employee.getDepartment());
        descriptor.setPayroll(employee.getPayroll());
        descriptor.setLeaveCounter(employee.getLeaveCounter());
        descriptor.setDateOfBirth(employee.getDateOfBirthOptional());
        descriptor.setDateOfJoining(employee.getDateOfJoiningOptional());
        descriptor.setTags(employee.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withDepartment(String department) {
        descriptor.setDepartment(new Department(department));
        return this;
    }


    /**
     * Sets the {@code Payroll} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withPayroll(String payroll) {
        try {
            descriptor.setPayroll(new Payroll(payroll));
        } catch (IllegalValueException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * Sets the {@code LeaveCounter} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withLeaveCounter(String leaveCounter) {
        descriptor.setLeaveCounter(new LeaveCounter(leaveCounter));
        return this;
    }

    /**
     * Sets the {@code dateOfBirth} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withDateOfBirth(String date) {
        descriptor.setDateOfBirth(Optional.ofNullable(LocalDate.parse(date)));
        return this;
    }

    /**
     * Sets the {@code dateOfJoining} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withDateOfJoining(String date) {
        descriptor.setDateOfJoining(Optional.ofNullable(LocalDate.parse(date)));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEmployeeDescriptor}
     * that we are building.
     */
    public EditEmployeeDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEmployeeDescriptor build() {
        return descriptor;
    }
}
