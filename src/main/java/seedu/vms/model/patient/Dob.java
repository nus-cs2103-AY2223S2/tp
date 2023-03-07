package seedu.vms.model.patient;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.util.AppUtil.checkArgument;
import static seedu.vms.logic.parser.ParserUtil.parseDate;

import java.time.LocalDateTime;

import seedu.vms.logic.parser.exceptions.ParseException;

/**
 * Represents a Patient's dob in the dob book.
 * It is represented as LocalDateTime with the time set to 0000.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class Dob {

    public static final String MESSAGE_CONSTRAINTS =
        "Date of birth can take any date earlier than today, and it should not be blank";

    public final LocalDateTime value;

    /**
     * Constructs an {@code Dob}.
     *
     * @param dob A valid date of birth.
     */
    public Dob(String dob) {
        requireNonNull(dob);
        checkArgument(isValidDob(dob), MESSAGE_CONSTRAINTS);
        try {
            value = parseDate(dob);
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Constructs an {@code Dob}.
     *
     * @param dob A valid date of birth.
     */
    public Dob(LocalDateTime dob) {
        requireNonNull(dob);
        LocalDateTime cleanDob = LocalDateTime.of(dob.getYear(), dob.getMonthValue(), dob.getDayOfMonth(), 0, 0);
        checkArgument(isValidDob(cleanDob), MESSAGE_CONSTRAINTS);
        value = cleanDob;
    }

    /**
     * Returns true if a given string is a valid date that is before than today
     */
    public static boolean isValidDob(String test) {
        LocalDateTime testDate;
        try {
            testDate = parseDate(test);
        } catch (ParseException e) {
            return false;
        }
        return testDate.isBefore(LocalDateTime.now());
    }

    /**
     * Returns true if a given string is a valid date that is before than today
     */
    public static boolean isValidDob(LocalDateTime test) {
        return test.isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return value.format(ISO_LOCAL_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Dob // instanceof handles nulls
                        && value.equals(((Dob) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
