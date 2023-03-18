package seedu.recipe.testutil;

import seedu.recipe.model.recipe.*;
import seedu.recipe.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {
    private Name name;
    private RecipeDuration duration;
    private RecipePortion portion;
    private Set<Tag> tags = new HashSet<>();
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder(Name name, RecipePortion portion, RecipeDuration duration,
                         Set<Tag> tags, List<Ingredient> ingredients, List<Step> steps) {
        this.name = name;
        this.portion = portion;
        this.duration = duration;
        this.tags.addAll(tags);
        this.ingredients.addAll(ingredients);
        this.steps.addAll(steps);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
        duration = recipeToCopy.getDuration();
        portion = recipeToCopy.getPortion();
        tags = new HashSet<>(recipeToCopy.getTags());
        ingredients = recipeToCopy.getIngredients();
    }

    public Recipe build() {
        Recipe out = new Recipe(name);
        out.setDuration(duration);
        out.setPortion(portion);
        out.setTags(tags.toArray(Tag[]::new));
        out.setIngredients(ingredients.toArray(Ingredient[]::new));
        out.setSteps(steps.toArray(Step[]::new));
        return out;
    }
}
