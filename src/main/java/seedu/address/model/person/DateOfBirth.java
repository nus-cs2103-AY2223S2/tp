package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Represents a Person's date of birth in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS =
            "Please give your date in the format DD/MM/YYYY"
            + " and do only give valid date inputs.";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";

    public static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("dd MMMM yyyy");

    public final String dateOfBirth;

    /**
     * Constructs a {@code dateOfBirth}.
     *
     * @param dateOfBirth A given date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDate(dateOfBirth), MESSAGE_CONSTRAINTS);
        String formatted = "28/12/2000";
        try {
            Date date = INPUT_FORMAT.parse(dateOfBirth);
            formatted = OUTPUT_FORMAT.format(date);

        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
        this.dateOfBirth = formatted;
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDate(String dateString) {

        if (!dateString.matches(VALIDATION_REGEX)) {
            return false;
        }

        String[] dateParts = dateString.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        return isValidInputs(day, month, year);
    }

    /**
     * Returns true if given day, month and year are valid
     */
    public static boolean isValidInputs(int day, int month, int year) {
        if (!isValidMonth(month)) {
            return false;
        }

        if (month == 2 && (day > 28 && !isLeapYear(year))) {
            return false;
        } else if (month == 2 && (day > 29 && isLeapYear(year))) {
            return false;
        } else if (month == 4 && day > 30) {
            return false;
        } else if (month == 6 && day > 30) {
            return false;
        } else if (month == 9 && day > 30) {
            return false;
        } else if (month == 11 && day > 30) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if given month is valid
     */
    public static boolean isValidMonth(int month) {
        return !(month < 1 || month > 12);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0);
    }

    @Override
    public String toString() {
        String output = this.dateOfBirth;
        Date date = null;
        try {
            date = OUTPUT_FORMAT.parse(output);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        output = INPUT_FORMAT.format(date);
        return output;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && dateOfBirth.equals(((DateOfBirth) other).dateOfBirth)); // state check
    }

    @Override
    public int hashCode() {
        return this.dateOfBirth.hashCode();
    }

}
