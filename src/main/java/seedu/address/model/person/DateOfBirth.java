package seedu.address.model.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's date of birth in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS =
            "Please give your date in the format DD/MM/YYYY";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";

    public static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");

    public String dateOfBirth;

    /**
     * Constructs a {@code dateOfBirth}.
     *
     * @param dateOfBirth A given gender.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDate(dateOfBirth), MESSAGE_CONSTRAINTS);
        try {
            Date date = inputFormat.parse(dateOfBirth);
            String formatted = outputFormat.format(date);
            this.dateOfBirth = formatted;
        } catch (Exception e) {
            this.dateOfBirth = "28/12/2000";
            System.out.println("Invalid date format");
        }
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String output = this.dateOfBirth;
        Date date = null;
        try {
            date = outputFormat.parse(output);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        output = inputFormat.format(date);
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
