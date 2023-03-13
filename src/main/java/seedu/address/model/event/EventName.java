package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name in the address book.
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "Event names should only contain alphanumeric and punctuations. "
            + "It should not be blank and contain punctuations only. "
            + "It should only begin with alphanumeric characters. ";

    /*
     * The first character of the event name must not be a whitespace or any other non-alphanumeric characters
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Punct} ]*";

    public final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public EventName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventName // instanceof handles nulls
                && name.equals(((EventName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
