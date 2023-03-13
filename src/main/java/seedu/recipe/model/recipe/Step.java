package seedu.recipe.model.recipe;

/**
 * Represents one of a recipe's steps for a recipe in the recipe book.
 * Guarantees: immutable;
 */

public class Step {

    public static final String MESSAGE_CONSTRAINTS =
            "Steps should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;
//    public final Recipe recipe;

    /**
     * Constructs a {@code Step}
     * @param description A valid description for the step
     */
    public Step(String description) {
        this.description = description;
//        this.recipe = recipe;
    }

    /**
     * Returns true if a given string is a valid step.
     */
    public static boolean isValidStep(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString(){
        return description;
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
