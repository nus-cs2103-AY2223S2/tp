package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module's code in the tracker.<p>
 * Guarantees: immutable, is valid as declared in {@link #isValidCode(String)}.
 */
public class ModuleCode implements Comparable<ModuleCode> {

    /** Message that specifies the constraints. */
    public static final String MESSAGE_CONSTRAINTS =
            "Module codes should begin with uppercase alphabet characters, followed by numeric characters, optionally "
                    + "followed by more uppercase alphabet characters";

    /** The regex for validating the module code. */
    public static final String VALIDATION_REGEX = "[A-Z]+[0-9]+[A-Z]*";

    /** The module code. */
    public final String code;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param code A valid code.
     * @throws IllegalArgumentException Indicates that {@code code} is an invalid module code.
     */
    public ModuleCode(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

    /**
     * Returns true if {@code test} is a valid module code.
     *
     * @param test The string to check if it is a valid module code.
     * @return True if {@code test} is a valid module code. Otherwise, false.
     */
    public static boolean isValidCode(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(ModuleCode o) {
        return code.compareTo(o.code);
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
