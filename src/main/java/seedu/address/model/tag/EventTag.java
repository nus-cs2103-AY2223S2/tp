package seedu.address.model.tag;

import seedu.address.model.event.EventName;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as it is taken from existing event name}
 */
public class EventTag {

    public static final String MESSAGE_CONSTRAINTS = "Event tag names should be alphanumeric";
    //public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final EventName eventTagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public EventTag(EventName tagName) {
        requireNonNull(tagName);
        //checkArgument(isValidTagName(tagName.toString()), MESSAGE_CONSTRAINTS);
        this.eventTagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return EventName.isValidName(test);
        //return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTag // instanceof handles nulls
                && eventTagName.equals(((EventTag) other).eventTagName)); // state check
    }

    @Override
    public int hashCode() {
        return eventTagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + eventTagName.toString() + ']';
    }

}
