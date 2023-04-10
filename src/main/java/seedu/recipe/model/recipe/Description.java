package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents a Description in a recipe.
 * Guarantees: immutable; is valid as declared in {@link #isValidDesc(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "A description must be a word or sentence.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum},.!?][\\p{Alnum} ,.!?]*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description a valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDesc(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDesc(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

}
