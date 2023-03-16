package vimification.model.task;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.AppUtil.checkArgument;

public class Type {

    public static final String MESSAGE_CONSTRAINTS = "";
    public static final String VALIDATION_REGEX = "";

    public final String value;

    public Type(String type) {
        // TODO : UNSUPPRESS checking
        requireNonNull(type);
        // checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        this.value = type;
    }

    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                        && value.equals(((Type) other).value)); // state check
    }
}

