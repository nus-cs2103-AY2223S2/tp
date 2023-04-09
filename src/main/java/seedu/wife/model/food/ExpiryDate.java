package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.wife.model.food.foodvalidator.ExpiryDateValidator;

/**
 * Represents a Food's expiry date.
 * Guarantees: Immutable; expiry date is validated in {@link ExpiryDateValidator#validate(String)}
 */
public class ExpiryDate implements Comparable<ExpiryDate> {
    public static final DateTimeFormatter VALID_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private final LocalDate expiryDate;

    /**
     * Constructs an {@code ExpiryDate} from String
     *
     * @param date Expiry date of the food item wish to add in the fridge.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        ExpiryDateValidator.validate(date);
        this.expiryDate = LocalDate.parse(date, VALID_DATE_FORMAT);
    }

    /**
     * Constructs an {@code ExpiryDate} from formatted date.
     * Only used for loading food item upon startup.
     *
     * @param date Expiry date of the food item wish to add in the fridge.
     */
    private ExpiryDate(LocalDate date) {
        this.expiryDate = date;
    }

    /**
     * Produces an expiry date object for loading food item upon start up.
     *
     * @param date Expiry date of the food item wish to add in the fridge.
     * @return ExpiryDate object.
     */
    public static ExpiryDate of(String date) {
        requireNonNull(date);
        ExpiryDateValidator.validateOnLoad(date);
        return new ExpiryDate(LocalDate.parse(date, VALID_DATE_FORMAT));
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

    @Override
    public int compareTo(ExpiryDate ed) {
        if (this.expiryDate.isBefore(ed.expiryDate)) {
            return -1;
        }

        if (this.expiryDate.isEqual(ed.expiryDate)) {
            return 0;
        }

        return 1;
    }
}
