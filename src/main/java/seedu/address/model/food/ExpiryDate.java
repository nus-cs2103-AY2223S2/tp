package seedu.address.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents a Food's expiry date.
 * Guarantees: Immutable; expiry date is validated in {@link #isValidDate(LocalDate)}
 */
public class ExpiryDate {
    public static final String MESSAGE_CONSTRAINT = "The food you wish to add is already expired.";
    private final LocalDate expiryDate;

    /**
     * Constructs an {@code ExpiryDate}
     *
     * @param expiryDate Expiry date of the food item wish to add in the fridge.
     */
    public ExpiryDate(LocalDate expiryDate) {
        requireNonNull(expiryDate);
        checkArgument(isValidDate(expiryDate), MESSAGE_CONSTRAINT);
        this.expiryDate = expiryDate;
    }

    /**
     * Validate if the expiry date inserted is after the current date.
     *
     * @param expiryDate Expiry date entered by the user.
     * @return True if the expiry date is after the current date, else False.
     */
    public static boolean isValidDate(LocalDate expiryDate) {
        LocalDate dateNow = LocalDate.now();
        return expiryDate.isAfter(dateNow);
    }

    @Override
    public boolean equals(Object otherDate) {
        return otherDate == this
                || (otherDate instanceof ExpiryDate
                && this.expiryDate.equals((ExpiryDate) otherDate));
    }

    @Override
    public String toString() {
        return this.expiryDate.toString();
    }
}
