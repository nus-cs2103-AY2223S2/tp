package seedu.address.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a drug's expiry date in the drug inventory
 */
public class ExpiryDate {

    public static final String MESSAGE_CONSTRAINTS = "Expiry dates should follow the DD-MM-YYYY format and only have"
            + "numerical digits separated by - as inputs";

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
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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
