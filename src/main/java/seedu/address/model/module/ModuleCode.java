package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

/**
 * Represents a module's code in the tracker.
 * Guarantees: immutable.
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should begin with uppercase alphabet characters, followed by numeric characters, optionally "
                    + "followed by more uppercase alphabet characters, and it should not be blank";

    public static final String VALIDATION_REGEX = "[A-Z]+[0-9]+[A-Z]*";

    private final String code;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param code A valid code.
     */
    public ModuleCode(String code) {
        requireNonNull(code);
        this.code = code;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleCode
                && code.equals(((ModuleCode) other).code));
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
