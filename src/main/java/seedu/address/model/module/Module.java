package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class  Module{
    public static final String MESSAGE_CONSTRAINTS = "Module names should be AYXXXXSX ";
    public static final String VALIDATION_REGEX = "^AY[0-9]{4}S(1|2) [A-Z]+[0-9]+[A-Z]*";

    public final String moduleName;

    public Module(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    public static boolean isValidModuleName(String test) {
        System.out.println(test);
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && moduleName.equals(((Module) other).moduleName)); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

    public String toString() {
        return '[' + moduleName + ']';
    }
}
