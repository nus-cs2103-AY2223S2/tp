package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * The type Code.
 */
public class Code {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Module code is missing, invalid or at the wrong position. It should be alphanumeric.";

    private static final String VALIDATION_REGEX = "^[A-Z]{2,4}[0-9]{4}[A-Z]{0,1}$";

    /**
     * The Code.
     */
    protected final String code;

    /**
     * Instantiates a new Code.
     *
     * @param code the code
     */
    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

    /**
     * Is valid code boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof Code
                && code.equals(((Code) obj).code));
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

}
