package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * The type Grade.
 */
public class Grade {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Grade should be either one of [A+, A, A-, B+, B, B-, C+, C, D+, D, F, S, U]";

    private static final List<String> VALID_GRADES = Arrays.asList(new String[] {
        "A+",
        "A",
        "A-",
        "B+",
        "B",
        "B-",
        "C+",
        "C",
        "D+",
        "D",
        "F",
        "S",
        "U"
    });

    /**
     * The Value.
     */
    protected final String value;

    /**
     * Instantiates a new Grade.
     *
     * @param value the value
     */
    public Grade(String value) {
        requireNonNull(value);
        checkArgument(isValidGrade(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Is valid grade boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidGrade(String test) {
        return VALID_GRADES.contains(test);
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

}
