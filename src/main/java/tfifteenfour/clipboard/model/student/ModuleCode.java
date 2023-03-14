package tfifteenfour.clipboard.model.student;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleCode(String)} (String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS = "Module codes should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String moduleCode;

    /**
     * Constructs a {@code moduleCode}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        this.moduleCode = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && moduleCode.equals(((ModuleCode) other).moduleCode)); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '{' + moduleCode + '}';
    }
}
