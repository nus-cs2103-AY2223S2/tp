package bookopedia.model.parcel;

import static bookopedia.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Parcel in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidParcelName(String)}
 */
public class Parcel {

    public static final String MESSAGE_CONSTRAINTS = "Parcels names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String parcelName;

    /**
     * Constructs a {@code Parcel}.
     *
     * @param parcelName A valid parcel name.
     */
    public Parcel(String parcelName) {
        requireNonNull(parcelName);
        checkArgument(isValidParcelName(parcelName), MESSAGE_CONSTRAINTS);
        this.parcelName = parcelName;
    }

    /**
     * Returns true if a given string is a valid parcel name.
     */
    public static boolean isValidParcelName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Parcel // instanceof handles nulls
                && parcelName.equals(((Parcel) other).parcelName)); // state check
    }

    @Override
    public int hashCode() {
        return parcelName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + parcelName + ']';
    }

}
