package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {
    public static final String DEFAULT_PICTURE_PATH = "data/employeepictures/default_employee.png";

    // Identity fields
    private final Name name;
    private final EmployeeId employeeId;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Department department;
    private final Payroll payroll;
    private final LeaveCounter leaveCounter;
    private final Optional<LocalDate> dateOfBirth;
    private final Optional<LocalDate> dateOfJoining;
    private PicturePath picturePath;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null except for Optional fields.
     */
    public Employee(Name name, EmployeeId employeeId, Phone phone, Email email, Address address,
                    Department department, Payroll payroll, LeaveCounter leaveCounter,
                    Optional<LocalDate> dateOfBirth, Optional<LocalDate> dateOfJoining,
                    Optional<PicturePath> picturePath, Set<Tag> tags) {
        requireAllNonNull(name, employeeId, phone, email, address, department, payroll, leaveCounter,
                dateOfBirth, dateOfJoining, tags);
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.payroll = payroll;
        this.leaveCounter = leaveCounter;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.picturePath = picturePath.orElseGet(() -> new PicturePath(""));
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public EmployeeId getEmployeeId() {
        return this.employeeId;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public int getLeaveCount() {
        return leaveCounter.getLeaveCount();
    }

    public LeaveCounter getLeaveCounter() {
        return leaveCounter;
    }

    public String getDateOfBirth() {
        return dateOfBirth.map(s -> s.toString()).orElse("");
    }

    public Optional<LocalDate> getDateOfBirthOptional() {
        return dateOfBirth;
    }

    public String getDateOfJoining() {
        return dateOfJoining.map(s -> s.toString()).orElse("");
    }

    public Optional<LocalDate> getDateOfJoiningOptional() {
        return dateOfJoining;
    }

    public PicturePath getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(PicturePath picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both employees have the same name, phone and email.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName())
                && otherEmployee.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return otherEmployee.getName().equals(getName())
                && otherEmployee.getEmployeeId().equals(getEmployeeId())
                && otherEmployee.getPhone().equals(getPhone())
                && otherEmployee.getEmail().equals(getEmail())
                && otherEmployee.getAddress().equals(getAddress())
                && otherEmployee.getDepartment().equals(getDepartment())
                && otherEmployee.getPayroll().equals(getPayroll())
                && otherEmployee.getLeaveCounter().equals(getLeaveCounter())
                && otherEmployee.getDateOfBirth().equals(getDateOfBirth())
                && otherEmployee.getDateOfJoining().equals(getDateOfJoining())
                && otherEmployee.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, employeeId, phone, email, address, department, payroll, leaveCounter,
                dateOfBirth, dateOfJoining, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Employee ID: ")
                .append(getEmployeeId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Department: ")
                .append(getDepartment())
                .append("; Salary: ")
                .append(getPayroll().getSalary())
                .append("; Day of payment: ")
                .append(getPayroll().getDayOfPayment())
                .append("; Leave count: ")
                .append(getLeaveCount())
                .append("; Date of Birth: ")
                .append(getDateOfBirth())
                .append("; Date of Joining: ")
                .append(getDateOfJoining());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
