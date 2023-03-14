package seedu.recipe.model.recipe;

import static seedu.recipe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.recipe.model.tag.Tag;

/**
 * Represents a Recipe in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Recipe {

    // Identity field
    private final Name name;

    // Data fields
    private Optional<RecipePortion> portion = Optional.empty();
    private Optional<RecipeDuration> duration = Optional.empty();
    private Optional<Set<Tag>> tags = Optional.empty();
    private Optional<List<Ingredient>> ingredients = Optional.empty();
    private Optional<List<Step>> steps = Optional.empty();

    /**
     * Only the name field is required. The rest are optional (but recommended)
     */
    public Recipe(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
    public List<Ingredient> getIngredients() {
        return ingredients.get();
    }

    public RecipePortion getPortion() {
        return portion.get();
    }

    public RecipeDuration getDuration() {
        return duration.get();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.get());
    }

    public List<Step> getSteps() {
        return steps.get();
    }

    public void setPortion(Optional<RecipePortion> portion) {
        this.portion = portion;
    }

    public void setDuration(Optional<RecipeDuration> duration) {
        this.duration = duration;
    }

    public void setTags(Optional<Set<Tag>> tags) {
        this.tags = tags;
    }

    public void setIngredients(Optional<List<Ingredient>> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(Optional<List<Step>> steps) {
        this.steps = steps;
    }


    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getName().equals(getName())
                && otherRecipe.getPortion().equals(getPortion())
                && otherRecipe.getDuration().equals(getDuration())
                && otherRecipe.getTags().equals(getTags())
                && otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getSteps().equals(getSteps());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, portion, duration, tags, ingredients, steps);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        if (portion.isPresent()) {
            builder.append("; Portion: ").append(getPortion());
        }

        if (duration.isPresent()) {
            builder.append("; Duration: ").append(getDuration());
        }

        if (steps.isPresent()) {
            Set<Tag> tags = getTags();
            if (!tags.isEmpty()) {
                builder.append("; Tags: ");
                tags.forEach(builder::append);
            }
        }

        if (ingredients.isPresent()) {
            StringBuilder ingredientBuilder = new StringBuilder("; Ingredients: ");
            List<Ingredient> ingredientList = ingredients.get();
            for (Ingredient ingredient : ingredientList) {
                ingredientBuilder.append(ingredient.toString() + ", ");
            }
            builder.append(ingredientBuilder);
        }

        if (steps.isPresent()) {
            StringBuilder stepsBuilder = new StringBuilder("; Steps: ");
            List<Step> stepList = steps.get();
            int counter = 1;
            for (Step step : stepList) {
                stepsBuilder.append(counter + ". " + step.toString() + ", ");
                counter++;
            }
            builder.append(stepsBuilder);
        }
        return builder.toString();
    }
}
