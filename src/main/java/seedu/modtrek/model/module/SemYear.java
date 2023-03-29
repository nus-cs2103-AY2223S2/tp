package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * SemYear denotes the Year and Semester when the module is taken.
 */
public class SemYear implements Comparable<SemYear> {

    public static final String MESSAGE_CONSTRAINTS =
            "SemYear should be alphanumeric in the format YxS(T)x, where the first x is a digit between 0 and 5,"
                    + " and the second x is a digit between 1 and 2.";

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

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(SemYear o) {
        return o.toString().compareTo(this.toString());
    }
}
