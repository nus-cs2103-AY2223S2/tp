package seedu.internship.model.event;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Event's Description.
 * Guarantees: immutable.
 */
public class EventDescription {

    // The first character must not be a whitespace, otherwise " " (a blank string) becomes a valid input.
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String EMPTY_MESSAGE = "No description available.";

    public final String descriptionMessage;

    /**
     * Constructs a {@code Description}.
     *
     * @param descriptionMessage A valid name for a Description.
     */
    public EventDescription(String descriptionMessage) {
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
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDescription // instanceof handles nulls
                && descriptionMessage.equals(((EventDescription) other)
                .descriptionMessage)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionMessage.hashCode();
    }
}
