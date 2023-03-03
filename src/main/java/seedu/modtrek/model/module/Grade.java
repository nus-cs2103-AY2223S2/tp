package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

public class Grade {

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

    protected final String value;

    public Grade(String value) {
        requireNonNull(value);
        checkArgument(isValidGrade(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

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
