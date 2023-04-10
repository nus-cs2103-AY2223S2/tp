package seedu.recipe.model.recipe;

import static seedu.recipe.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.recipe.model.util.IngredientUtil.ingredientTableToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.recipe.model.recipe.exceptions.RecipeDurationNotPresentException;
import seedu.recipe.model.recipe.exceptions.RecipePortionNotPresentException;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;

/**
 * Represents a Recipe in the recipe book.
 * Guarantees: details are present and not null, field values are validated.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Recipe {

    // Identity field
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Step> steps = new ArrayList<>();
    private final HashMap<Ingredient, IngredientInformation> ingredientTable = new HashMap<>();

    // Data fields
    private Optional<RecipePortion> portion = Optional.empty();
    private Optional<RecipeDuration> duration = Optional.empty();

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

    public HashMap<Ingredient, IngredientInformation> getIngredients() {
        return ingredientTable;
    }

    public void setIngredients(IngredientBuilder... ingredients) {
        for (IngredientBuilder ingredientBuilder : ingredients) {
            ingredientTable.putAll(
                ingredientBuilder.build());
        }
    }

    public void setIngredients(Map<? extends Ingredient, ? extends IngredientInformation> ingredientMap) {
        this.ingredientTable.putAll(ingredientMap);
    }

    public Set<Ingredient> getIngredientList() {
        return ingredientTable.keySet();
    }

    public RecipePortion getPortion() {
        portion.orElseThrow(RecipePortionNotPresentException::new);
        return portion.get();
    }

    public void setPortion(RecipePortion portion) {
        this.portion = Optional.ofNullable(portion);
    }

    public RecipeDuration getDuration() {
        duration.orElseThrow(RecipeDurationNotPresentException::new);
        return duration.get();
    }

    public void setDuration(RecipeDuration duration) {
        this.duration = Optional.ofNullable(duration);
    }

    // nullable variants of getPortion and getDuration
    // when we are okay with receiving null
    public RecipePortion getPortionNullable() {
        return portion.orElse(null);
    }

    public RecipeDuration getDurationNullable() {
        return duration.orElse(null);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return A set of the tags associated with this Recipe.
     */
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Tag... tags) {
        this.tags.addAll(Arrays.asList(tags));
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(Step... steps) {
        this.steps.addAll(List.of(steps));
    }

    /**
     * Returns true if both recipes have the same name.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
            && otherRecipe.getName().equals(getName());
    }

    /**
     * Returns true if both recipes have the same identity and data fields.
     * This defines a stronger notion of equality between two recipes.
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
            && otherRecipe.portion.equals(portion)
            && otherRecipe.duration.equals(duration)
            && otherRecipe.getTags().equals(getTags())
            && otherRecipe.getIngredients().equals(getIngredients())
            && otherRecipe.getSteps().equals(getSteps());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, portion, duration, tags, ingredientTable, steps);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName());

        portion.ifPresent(p -> stringBuilder.append(";\nPortion: ").append(p));

        duration.ifPresent(d -> stringBuilder.append(";\nDuration: ").append(d));

        if (!tags.isEmpty()) {
            stringBuilder.append(";\nTags: ");
            ArrayList<Tag> tags = new ArrayList<>(this.tags);
            tags.sort(Comparator.comparing(t -> t.tagName));
            tags.forEach(stringBuilder::append);
        }

        if (!ingredientTable.isEmpty()) {
            stringBuilder.append(";\nIngredients:\n");
            stringBuilder.append(ingredientTableToString(ingredientTable));
        }

        if (!steps.isEmpty()) {
            stringBuilder.append(";\nSteps: ");
            for (int i = 0; i < steps.size(); i++) {
                stringBuilder.append(String.format("%s. %s,\n", i + 1, steps.get(i)));
            }
        }
        return stringBuilder.toString();
    }
}
