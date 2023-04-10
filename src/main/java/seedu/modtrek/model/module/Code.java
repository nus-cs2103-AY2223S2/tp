package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * Code denotes the Module Code for a module.
 */
public class Code {

    public static final String MESSAGE_CONSTRAINTS = "Module code is invalid.\n"
            + "It should be in the form of <Module Prefix><4 number code><optional alphabet>.\n"
                    + "Module Prefix should be 2 to 4 alphabets.\n" + "Examples: CS2109S, gess1025";

    public static final String MESSAGE_MISSING_DETAIL = "Missing module code after /m.";

    private static final String VALIDATION_REGEX = "^[A-Z]{2,4}[0-9]{4}[A-Z]{0,1}$";

    protected final String code;

    /**
     * Instantiates a Code. The code cannot be null and must be valid.
     *
     * @param code the code
     */
    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

    /**
     * Checks if the code is valid based on the regex.
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
