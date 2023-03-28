package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final Name name;
    private final EmployeeId employeeId;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Department department;
    private Payroll payroll;
    private int leaveCount;
    private LocalDate dateOfBirth = null;
    private LocalDate dateOfJoining = null;

    private PicturePath picturePath;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null except picturePathString.
     */
    public Employee(Name name, EmployeeId employeeId, Phone phone, Email email, Address address,
                    Department department, Set<Tag> tags) {
        requireAllNonNull(name, employeeId, phone, email, address, department, tags);
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.picturePath = new PicturePath("src/main/resources/employeepictures/default.png");
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null except picturePathString. Includes new fields (payroll,
     * leave count, date of birth, date of joining)
     */
    public Employee(Name name, EmployeeId employeeId, Phone phone, Email email, Address address,
                    Department department, Payroll payroll, int leaveCount, LocalDate dateOfBirth,
                    LocalDate dateOfJoining, Set<Tag> tags) {
        requireAllNonNull(name, employeeId, phone, email, address, department, payroll, leaveCount,
                dateOfBirth, dateOfJoining, tags);
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.leaveCount = leaveCount;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.picturePath = new PicturePath("src/main/resources/employeepictures/default.png");
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, EmployeeId employeeId, Phone phone, Email email, Address address,
                    Department department, PicturePath picturePath, Set<Tag> tags) {
        requireAllNonNull(name, employeeId, phone, email, address, department, tags);
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.picturePath = picturePath;
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
        return leaveCount;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
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
     * Returns true if both employees have the same employee ID.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployeeID(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getEmployeeId().equals(getEmployeeId());
    }

    /**
     * Returns true if both employees have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName());
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
                && otherEmployee.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, employeeId, phone, email, address, department, tags);
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
                .append(getDepartment());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
