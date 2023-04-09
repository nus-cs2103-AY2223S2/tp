package seedu.address.testutil;

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

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.employee.Employee;
import seedu.address.model.tag.Tag;


/**
 * A utility class for Employee.
 */
public class EmployeeUtil {

    /**
     * Returns an add command string for adding the {@code employee}.
     */
    public static String getAddCommand(Employee employee) {
        return AddCommand.COMMAND_WORD + " " + getEmployeeDetails(employee);
    }

    /**
     * Returns the part of command string for the given {@code employee}'s details.
     */
    public static String getEmployeeDetails(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + employee.getName().fullName + " ");
        sb.append(PREFIX_PHONE + employee.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + employee.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + employee.getAddress().value + " ");
        sb.append(PREFIX_DEPARTMENT + employee.getDepartment().value + " ");
        sb.append(PREFIX_PAYROLL + employee.getPayroll().toString() + " ");
        sb.append(PREFIX_LEAVE_COUNT + String.valueOf(employee.getLeaveCount()) + " ");
        if (!employee.getDateOfBirthOptional().isEmpty()) {
            sb.append(PREFIX_DATE_OF_BIRTH + employee.getDateOfBirth() + " ");
        }
        if (!employee.getDateOfJoiningOptional().isEmpty()) {
            sb.append(PREFIX_DATE_OF_JOINING + employee.getDateOfJoining() + " ");
        }
        employee.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEmployeeDescriptor}'s details.
     */
    public static String getEditEmployeeDescriptorDetails(EditCommand.EditEmployeeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getDepartment()
                .ifPresent(department -> sb.append(PREFIX_DEPARTMENT).append(department.value).append(" "));
        descriptor.getPayroll()
                .ifPresent(payroll -> sb.append(PREFIX_PAYROLL).append(payroll.toString()).append(" "));
        descriptor.getLeaveCounter()
                .ifPresent(leaveCounter -> sb.append(PREFIX_LEAVE_COUNT).append(leaveCounter.toString()).append(" "));
        descriptor.getDateOfBirth()
                .ifPresent(date -> sb.append(PREFIX_DATE_OF_BIRTH).append(date).append(" "));
        descriptor.getDateOfJoining()
                .ifPresent(date -> sb.append(PREFIX_DATE_OF_JOINING).append(date).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
