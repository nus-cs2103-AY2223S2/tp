package seedu.recipe.logic.util;

import seedu.recipe.commons.util.CollectionUtil;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Stores the details to edit the recipe with. Each non-empty field value will replace the
 * corresponding field value of the recipe.
 */
public class RecipeDescriptor {
    private Name name;
    private RecipeDuration duration;
    private RecipePortion portion;
    private Set<Tag> tags;
    private List<Ingredient> ingredients;
    private List<Step> steps;

    public RecipeDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public RecipeDescriptor(RecipeDescriptor toCopy) {
        setName(toCopy.name);
        setDuration(toCopy.duration);
        setPortion(toCopy.portion);
        setTags(toCopy.tags);
        setIngredients(toCopy.ingredients);
        setSteps(toCopy.steps);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, duration, portion, tags, ingredients, steps);
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<RecipeDuration> getDuration() {
        return Optional.ofNullable(duration);
    }

    public void setDuration(RecipeDuration duration) {
        this.duration = duration;
    }

    public Optional<RecipePortion> getPortion() {
        return Optional.ofNullable(portion);
    }

    public void setPortion(RecipePortion portion) {
        this.portion = portion;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    public Optional<List<Ingredient>> getIngredients() {
        return (ingredients != null) ? Optional.of(Collections.unmodifiableList(ingredients)) : Optional.empty();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = (ingredients != null) ? new ArrayList<>(ingredients) : null;
    }

    public Optional<List<Step>> getSteps() {
        return (steps != null) ? Optional.of(Collections.unmodifiableList(steps)) : Optional.empty();
    }

    public void setSteps(List<Step> steps) {
        this.steps = (steps != null) ? new ArrayList<>(steps) : null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeDescriptor)) {
            return false;
        }

        // state check
        RecipeDescriptor e = (RecipeDescriptor) other;

        return getName().equals(e.getName())
                && getDuration().equals(e.getDuration())
                && getPortion().equals(e.getPortion())
                && getTags().equals(e.getTags())
                && getIngredients().equals(e.getIngredients())
                && getSteps().equals(e.getSteps());
    }
}
