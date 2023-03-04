package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Internship's Description in the internship catalogue.
 * Guarantees: immutable.
 */
public class Description {

    public static final String EMPTY_MESSAGE = "No description available.";
    public final String descriptionMessage;

    /**
     * Constructs a {@code Description}.
     *
     * @param descriptionMessage A valid name for a Description.
     */
    public Description(String descriptionMessage) {
        requireNonNull(descriptionMessage);
        this.descriptionMessage = descriptionMessage;
    }

    /**
     * Returns true if the descriptionMessage is an empty string or only contains whitespace. Else, returns false.
     *
     * @return A boolean value.
     */
    public boolean isEmpty() {
        return descriptionMessage.stripTrailing().equals("");
    }
    @Override
    public String toString() {
        return isEmpty() ? EMPTY_MESSAGE : descriptionMessage;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && descriptionMessage.equals(((Description) other).descriptionMessage)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionMessage.hashCode();
    }

}
