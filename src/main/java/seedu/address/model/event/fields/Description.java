package seedu.address.model.event.fields;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Events's Description in the Calendar.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should not be blank";

    public final String description;

    /**
     * Creates a {@code Description}.
     */
    public Description(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * @return true if {@code description} is not blank.
     */
    public static boolean isValidDescription(String description) {
        return !description.isBlank();
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || ((other instanceof Description)
                && description.equals(((Description) other).description));
    }
}
