package seedu.recipe.model.recipe;

/**
 * Represents one of a recipe's steps for a recipe in the recipe book.
 * Guarantees: immutable;
 */

public class Step {

    public final String description;
    public final Recipe recipe;

    /**
     * Constructs a {@code Step}
     * @param description A valid description for the step
     * @param recipe Recipe for which the step is for
     */
    public Step(String description, Recipe recipe) {
        this.description = description;
        this.recipe = recipe;
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
