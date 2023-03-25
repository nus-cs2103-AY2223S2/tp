package seedu.wife.model.food;

import seedu.wife.model.food.foodvalidator.ExpiryDateValidator;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Food's expiry date.
 * Guarantees: Immutable; expiry date is validated in {@link #isValidDate(LocalDate)}
 */
public class ExpiryDate {
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Format of your date is incorrect. "
            + "Please insert using the format DD-MM-YYYY";
    public static final String FORMAT_MESSAGE_CONSTRAINTS = "Format of your date is incorrect. "
            + "Please insert using the format DD-MM-YYYY";

    private final LocalDate expiryDate;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
    private final DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Construct an {@code ExpiryDate} from String
     *
     * @param date Expiry date of the food item wish to add in the fridge.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        ExpiryDateValidator.validate(date);
        this.expiryDate = LocalDate.parse(date, validFormat);
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
                && expiryDate.equals(((ExpiryDate) otherDate).expiryDate));
    }

    @Override
    public String toString() {
        return this.expiryDate.format(validFormat);
    }
}
