package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents a Step in a list of Steps in a recipe.
 * Guarantees: immutable; is valid as declared in {@link #isValidStep(String)}
 */
public class Step {

    public static final String MESSAGE_CONSTRAINTS = "A step must be a word or sentence.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum},.!?][\\p{Alnum} ,.!?]*";

    public final String step;

    /**
     * Constructs a {@code Step}.
     *
     * @param step A valid step.
     */
    public Step(String step) {
        requireNonNull(step);
        checkArgument(isValidStep(step), MESSAGE_CONSTRAINTS);
        this.step = step;
    }

    /**
     * Returns true if a given string is a valid step.
     */
    public static boolean isValidStep(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.step;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Step // instanceof handles nulls
                && this.step.equals(((Step) other).step)); // state check
    }

    @Override
    public int hashCode() {
        return this.step.hashCode();
    }

}
