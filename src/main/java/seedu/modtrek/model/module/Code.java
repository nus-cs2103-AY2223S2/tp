package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

public class Code {

    public static final String MESSAGE_CONSTRAINTS =
            "Module code should not be blank and is alphanumeric";

    private static final String VALIDATION_REGEX = "^[A-Z]{2,3}[0-9]{4}[A-Z]{0,1}$";

    protected final String code;

    public Code(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

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
