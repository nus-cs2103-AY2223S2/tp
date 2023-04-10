package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

import java.util.Map;
import java.util.Set;

/**
 * Grade denotes the grading based on NUS system for each module.
 */
public class Grade implements Comparable<Grade> {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should be either one of [A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U].";

    public static final String MESSAGE_MISSING_DETAIL = "Missing grade after /g.";

    public static final String NO_GRADE = "NO GRADE";

    private static Map<String, Double> gradeToPoints = Map.ofEntries(
        Map.entry("A+", 5.0),
        Map.entry("A", 5.0),
        Map.entry("A-", 4.5),
        Map.entry("B+", 4.0),
        Map.entry("B", 3.5),
        Map.entry("B-", 3.0),
        Map.entry("C+", 2.5),
        Map.entry("C", 2.0),
        Map.entry("D+", 1.5),
        Map.entry("D", 1.0),
        Map.entry("F", 0.0),
        Map.entry("S", 5.0),
        Map.entry("U", 0.0),
        Map.entry("NO GRADE", -1.0) // Empty grade
    );

    private static Map<String, Double> gradeToTagPoints = Map.ofEntries(
            Map.entry("A+", 5.0),
            Map.entry("A", 4.5),
            Map.entry("A-", 4.2),
            Map.entry("B+", 4.0),
            Map.entry("B", 3.5),
            Map.entry("B-", 3.0),
            Map.entry("C+", 2.5),
            Map.entry("C", 2.0),
            Map.entry("D+", 1.5),
            Map.entry("D", 1.0),
            Map.entry("F", 0.0),
            Map.entry("S", -1.0),
            Map.entry("U", -1.1),
            Map.entry("NO GRADE", -1.2) // Empty grade
    );

    private static final Set<String> VALID_GRADES = gradeToPoints.keySet();

    protected final String value;

    /**
     * Instantiates a new Grade. Grade cannot be null and must be valid.
     *
     * @param value the value
     */
    public Grade(String value) {
        requireNonNull(value);
        checkArgument(isValidGrade(value), MESSAGE_CONSTRAINTS);
        if (value.isEmpty()) {
            this.value = NO_GRADE;
        } else {
            this.value = value;
        }
    }

    /**
     * Checks if the Grade is valid based on a list of valid grades.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidGrade(String test) {
        requireNonNull(test);
        return test.isEmpty() || test.equals(NO_GRADE) || VALID_GRADES.contains(test);
    }

    public double toPoints() {
        return gradeToPoints.get(value);
    }

    public double toTagPoints() {
        return gradeToTagPoints.get(value);
    }

    public boolean isEmpty() {
        return value.equals(NO_GRADE);
    }

    public boolean isGradeable() {
        return !value.equals("U");
    }

    public boolean isSatisfactory() {
        return value.equals("S");
    }

    public boolean isPass() {
        return !value.equals("F") || isGradeable();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof Grade
                && value.equals(((Grade) obj).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Grade o) {
        if (o.toTagPoints() > this.toTagPoints()) {
            return 1;
        } else if (o.toTagPoints() < this.toTagPoints()) {
            return -1;
        }
        return 0;
    }
}
