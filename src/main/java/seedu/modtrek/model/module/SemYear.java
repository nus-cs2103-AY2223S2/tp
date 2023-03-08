package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * The type Sem year.
 */
public class SemYear {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "SemYear should be alphanumeric in the format YxSx, where x are digits";

    private static final String VALIDATION_REGEX = "^Y[0-5]S[T]{0,1}[1-2]$";

    /**
     * The Semyear.
     */
    protected final String semyear;

    /**
     * Instantiates a new Sem year.
     *
     * @param semyear the semyear
     */
    public SemYear(String semyear) {
        requireNonNull(semyear);
        checkArgument(isValidSemYear(semyear), MESSAGE_CONSTRAINTS);
        this.semyear = semyear;
    }

    /**
     * Is valid sem year boolean.
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
