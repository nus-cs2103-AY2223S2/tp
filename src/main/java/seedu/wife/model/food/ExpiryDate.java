package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.wife.model.food.foodvalidator.ExpiryDateValidator;

/**
 * Represents a Food's expiry date.
 * Guarantees: Immutable; expiry date is validated in {@link ExpiryDateValidator#validate(String)}
 */
public class ExpiryDate {
    public static final DateTimeFormatter VALID_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private final LocalDate expiryDate;

    /**
     * Construct an {@code ExpiryDate} from String
     *
     * @param date Expiry date of the food item wish to add in the fridge.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        ExpiryDateValidator.validate(date);
        this.expiryDate = LocalDate.parse(date, VALID_DATE_FORMAT);
    }

    @Override
    public boolean equals(Object otherDate) {
        return otherDate == this
                || (otherDate instanceof ExpiryDate
                && expiryDate.equals(((ExpiryDate) otherDate).expiryDate));
    }

    @Override
    public String toString() {
        return this.expiryDate.format(VALID_DATE_FORMAT);
    }
}
