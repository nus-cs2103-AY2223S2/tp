package seedu.connectus.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.util.AppUtil.checkArgument;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's birthday in ConnectUS.
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS = "Birthday should be of the format DD/MM/YYYY\n"
            + "Format: " + PREFIX_BIRTHDAY + "BIRTHDAY";

    public static final String MESSAGE_FUTURE_DATE = "Birthday should be in the past";
    public static final String MESSAGE_TOO_OLD_DATE = "Birthday must be less than 200 years ago";

    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4})$";

    private final LocalDate value;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);

        String[] date = birthday.split("/");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        value = LocalDate.of(year, month, day);
        checkArgument(isValidDate(value), MESSAGE_FUTURE_DATE);
        checkArgument(!isTooOldDate(value), MESSAGE_TOO_OLD_DATE);

    }

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthday A birthday as LocalDate.
     */
    public Birthday(LocalDate birthday) {
        requireNonNull(birthday);
        value = birthday;
    }

    /**
     * Returns true if the birthday is in the next 2 months.
     *
     * @return boolean True if birthday is in the next 2 months.
     */
    public boolean isUpcoming() {
        LocalDate today = LocalDate.now();
        int daysUntilBirthday = value.getDayOfYear() - today.getDayOfYear();
        if (daysUntilBirthday <= 61 && daysUntilBirthday >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given date is valid (in the past).
     */
    public static boolean isValidDate(LocalDate date) {
        // birthday is valid only if it is in the past
        // it can also not be more than 200 years ago
        return date.isBefore(LocalDate.now());
    }

    private static boolean isTooOldDate(LocalDate date) {
        return date.isBefore(LocalDate.now().minusYears(200));
    }

    public LocalDate getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                        && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
