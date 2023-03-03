package seedu.modtrek.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

public class SemYear {

    public static final String MESSAGE_CONSTRAINTS =
            "SemYear should be alphanumeric in the format YxSx, where x are digits";

    private static final String VALIDATION_REGEX = "Y\\dS\\d";

    protected final String semyear;

    public SemYear(String semyear) {
        requireNonNull(semyear);
        checkArgument(isValidSemYear(semyear), MESSAGE_CONSTRAINTS);
        this.semyear = semyear;
    }

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
