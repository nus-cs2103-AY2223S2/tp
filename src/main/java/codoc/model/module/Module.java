package codoc.model.module;

import static java.util.Objects.requireNonNull;

import codoc.commons.util.AppUtil;

/**
 * Represents a Module in CoDoc.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleName(String)}
 */
public class Module {
    public static final String MESSAGE_CONSTRAINTS = "Module names should be AY____S_ "
            + "{MODULE_CODE}{NUMBER}{OPTIONAL_ALPHABET} "
            + "where the 4 digits after AY is 2, 2 digit number increment: eg. 2223 "
            + "and the digit after S is either 1 or 2";
    public static final String YEAR_CONSTRAINTS = "Year should be 2, 2 digits numbers, where one is incremented "
            + "eg. 2223";
    public static final String VALIDATION_REGEX = "^AY[0-9]{4}S[12] [A-Z]+[0-9]+[A-Z]*";

    public final String moduleName;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleName A valid module name.
     */
    public Module(String moduleName) {
        requireNonNull(moduleName);
        moduleName = moduleName.toUpperCase();
        AppUtil.checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        AppUtil.checkArgument(isValidModuleYear(moduleName), YEAR_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the year part of module is valid
     */
    public static boolean isValidModuleYear(String test) {
        String firstDigit = test.substring(2, 4);
        String secondDigit = test.substring(4, 6);
        if (firstDigit.equals("99")) {
            return secondDigit.equals("00");
        }
        return Integer.parseInt(firstDigit) + 1 == Integer.parseInt(secondDigit);
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

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + moduleName + ']';
    }
}
