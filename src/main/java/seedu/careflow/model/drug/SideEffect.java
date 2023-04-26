package seedu.careflow.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's possible side effects in the drug inventory
 */
public class SideEffect {
    public static final String MESSAGE_CONSTRAINTS = "Side effects should be in sentence form "
            + "it cannot be blank and should be less than 1500 characters long";

    /*
     * The first 3 consecutive characters are upper or lowercase alphabetical characters,
     * followed by 0 or more alphabetical, whitespace characters or commas.
     */
    public static final String VALIDATION_REGEX = "[A-Za-z0-9][\\p{Graph}\\p{Space}]{0,1499}";

    public final String sideEffect;

    /**
     * Constructs an {@code SideEffect}.
     *
     * @param sideEffect A valid side effect.
     */
    public SideEffect(String sideEffect) {
        requireNonNull(sideEffect);
        checkArgument(isValidSideEffect(sideEffect), MESSAGE_CONSTRAINTS);
        this.sideEffect = sideEffect;
    }

    /**
     * Returns true if a given string is a valid side effect.
     */
    public static boolean isValidSideEffect(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return sideEffect;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SideEffect // instanceof handles nulls
                && sideEffect.equals(((SideEffect) other).sideEffect)); // state check
    }

    @Override
    public int hashCode() {
        return sideEffect.hashCode();
    }
}
