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
            "Please give your date in the format DD/MM/YYYY";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";

    public static final SimpleDateFormat INPUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("dd MMMM yyyy");

    public final String dateOfBirth;

    /**
     * Constructs a {@code dateOfBirth}.
     *
     * @param dateOfBirth A given gender.
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
