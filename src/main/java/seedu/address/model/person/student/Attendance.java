package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;


/**
 * Represents the Attendance of a Student object.
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "Attendance must be a date in the format of dd/mm/yyyy";

    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4}";
    public static final String VALIDATION_REGEX2 = "[TF]";
    private String value;
    private LocalDate date;


    /**
     * Constructs an {@code Address}.
     *
     * @param value A string representation of a date
     */
    public Attendance(String value) {
        requireNonNull(value);
        checkArgument(isValidAttendance(value), MESSAGE_CONSTRAINTS);
        this.value = value;
        if (value.equals("T")) {
            this.date = LocalDate.now();
            this.value = this.date.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else if (!value.equals("F")) {
            this.date = LocalDate.parse(value, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    /**
     * Constructs an {@code Address}.
     *
     * @param value A LocalDate representation of an attendance
     */
    public Attendance(LocalDate value) {
        requireNonNull(value);
        this.value = value.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.date = value;
    }

    /**
     * Returns true if a given string is a valid date
     */
    public static boolean isValidAttendance(String test) {
        // Default address
        if (test.equals("Insert student attendance here!")) {
            return true;
        }

        return test.matches(VALIDATION_REGEX) || test.matches(VALIDATION_REGEX2);
    }

    /**
     * Returns true if the attendance is present on the given date.
     * @param currDate The date to check against
     * @return boolean value to indicate if the attendance is present on the given date.
     */
    public boolean isPresent(LocalDate currDate) {
        if (value.equals("F")) {
            return false;
        }
        return currDate.isEqual(date);
    }

    /**
     * Returns true if there is a localdate associated with the attendance.
     * @return boolean value to indicate if there is a localdate associated with the attendance.
     */
    public boolean isDate() {
        return this.date != null;
    }

    /**
     * Notes that the student is absent
     * @return Attendance object with value "F"
     */
    public boolean isAbsent() {
        return value.equals("F");
    }

    /**
     * Returns true if a given string is the default string given when the attendance isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultAttendance(String test) {
        if (test.equals("Insert student attendance here!")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance// instanceof handles nulls
                && value.equals(((Attendance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
