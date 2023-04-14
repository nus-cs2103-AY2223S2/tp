package seedu.address.model.medicine;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Medicine in the app.
 */
public class Medicine {

    public static final String MESSAGE_CONSTRAINTS = "Medicine names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String medicineName;

    /**
     * Constructs a {@code Medicine}.
     *
     * @param medicineName A valid tag name.
     */
    public Medicine(String medicineName) {
        requireNonNull(medicineName);
        checkArgument(isValidMedicineName(medicineName), MESSAGE_CONSTRAINTS);
        this.medicineName = medicineName;
    }

    /**
     * Returns true if a given string is a valid medicine name.
     */
    public static boolean isValidMedicineName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medicine // instanceof handles nulls
                && medicineName.equals(((Medicine) other).medicineName)); // state check
    }

    @Override
    public int hashCode() {
        return medicineName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + medicineName + ']';
    }

}
