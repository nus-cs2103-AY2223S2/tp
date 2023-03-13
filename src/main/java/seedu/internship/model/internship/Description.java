package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Internship's Description in the internship catalogue.
 * Guarantees: immutable.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String EMPTY_MESSAGE = "No description available.";
    public final String descriptionMessage;

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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

    /**
     * Returns true if a given string is a valid Description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
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
