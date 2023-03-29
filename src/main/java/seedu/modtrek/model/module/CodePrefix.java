package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * The type CodePrefix representing the prefix of a module code.
 */
public class CodePrefix {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Module code prefix is invalid. It should be between 2 and 4 alphabetic characters.";

    public static final String MESSAGE_MISSING_DETAIL = "Missing module code prefix after /m.";

    private static final String VALIDATION_REGEX = "^[A-Z]{2,4}$";

    /**
     * The CodePrefix.
     */
    protected final String codePrefix;

    /**
     * Instantiates a new CodePrefix.
     *
     * @param codePrefix the code prefix
     */
    public CodePrefix(String codePrefix) {
        requireNonNull(codePrefix);
        checkArgument(isValidCodePrefix(codePrefix), MESSAGE_CONSTRAINTS);
        this.codePrefix = codePrefix;
    }

    /**
     * Is valid code prefix boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidCodePrefix(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return codePrefix;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof CodePrefix
                && codePrefix.equals(((CodePrefix) obj).codePrefix));
    }

    @Override
    public int hashCode() {
        return codePrefix.hashCode();
    }
}
