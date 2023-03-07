package seedu.address.model.event.fields;

/**
 * Represents an Events's Description in the Calendar.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, "
                    + "and it should not be blank";

    public static final String VALIDATION = "";

    public final String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static boolean isValidDescription(String description) {
        return !description.equals(VALIDATION);
    }
}
