package seedu.address.model.drug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's expiry date in the drug inventory
 */
public class ExpiryDate {
    public static final String MESSAGE_CONSTRAINTS = "Expiry dates should follow the DD-MM-YYYY format and only have"
            + "numerical digits separated by - as inputs";

    /*
     * the format of "dd-mm-yyyy", where "dd" represents the day,
     * "mm" represents the month, and "yyyy" represents the year
     * with all characters being numerical digits separated by hypens.
     */
    public static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4}";

    public final LocalDate expiryDate;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param expiryDate A valid expiryDate.
     */
    public ExpiryDate(String expiryDate) {
        requireNonNull(expiryDate);
        checkArgument(isValidExpiryDate(expiryDate), MESSAGE_CONSTRAINTS);
        this.expiryDate = LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Returns true if a given string is a valid expiry date.
     */
    public static boolean isValidExpiryDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return expiryDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && expiryDate.equals(((ExpiryDate) other).expiryDate)); // state check
    }

    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }
}
