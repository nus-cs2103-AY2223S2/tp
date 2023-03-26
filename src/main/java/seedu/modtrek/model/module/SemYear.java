package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * SemYear denotes the Year and Semester when the module is taken.
 */
public class SemYear {

    public static final String MESSAGE_CONSTRAINTS =
            "SemYear should be alphanumeric in the format YxS(T)x, where x are digits.";

    public static final String MESSAGE_MISSING_DETAIL = "Missing sem-year after /y.";

    private static final String VALIDATION_REGEX = "^Y[0-5]S[T]{0,1}[1-2]$";

    protected final String semyear;

    /**
     * Instantiates a new SemYear. This cannot be null and must be valid.
     *
     * @param semyear the semyear
     */
    public SemYear(String semyear) {
        requireNonNull(semyear);
        checkArgument(isValidSemYear(semyear), MESSAGE_CONSTRAINTS);
        this.semyear = semyear;
    }

    /**
     * Checks if the SemYear is valid.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidSemYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return semyear;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof SemYear
                && semyear.equals(((SemYear) obj).semyear));
    }

    @Override
    public int hashCode() {
        return semyear.hashCode();
    }

}
