package seedu.recipe.model.recipe;

import static seedu.recipe.commons.util.CollectionUtil.requireAllNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.recipe.model.tag.Tag;



/**
 * Represents a Recipe in the recipe book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    // Message constraints for recipe and validation regex will
    // only be used for the find command at the moment
    public static final String MESSAGE_CONSTRAINTS =
            "A recipe should only be a word or sentence.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum},.!?][\\p{Alnum} ,.!?]*";

    // Identity fields
    private final Title title;
    private final Description desc;

    // Data fields
    private final Set<Ingredient> ingredients;
    private final List<Step> steps;
    private final Set<Tag> tags;

    private boolean isStar;



    /**
     * Every field must be present and not null except for {@code isStar} which can be left blank.
     * In this case, set it to false automatically.
     */
    public Recipe(Title title, Description desc, Set<Ingredient> ingredients, List<Step> steps,
                  Set<Tag> tags) {
        requireAllNonNull(title, desc, ingredients, steps, tags);
        this.title = title;
        this.desc = desc;
        this.ingredients = ingredients;
        this.steps = steps;
        this.tags = tags;
        this.isStar = false;
    }

    /**
     * Every field is present and not null.
     */
    public Recipe(Title title, Description desc, Set<Ingredient> ingredients, List<Step> steps,
                  Set<Tag> tags, boolean isStar) {
        requireAllNonNull(title, desc, ingredients, steps, tags);
        this.title = title;
        this.desc = desc;
        this.ingredients = ingredients;
        this.steps = steps;
        this.tags = tags;
        this.isStar = isStar;
    }

    public Title getTitle() {
        return this.title;
    }

    public Description getDesc() {
        return this.desc;
    }

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }
    public List<Step> getSteps() {
        return this.steps;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public boolean isStarred() {
        return this.isStar;
    }

    public static boolean isValidRecipe(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    /**
     * Stars the recipe.
     */
    public void star() {
        this.isStar = true;
    }

    /**
     * Unstars the recipe.
     */
    public void unstar() {
        this.isStar = false;
    }

    public Double getCost() {
        Double cost = 0d;
        for (Ingredient i : ingredients) {
            cost += i.quantity * i.pricePerUnit;
        }

        BigDecimal bd = new BigDecimal(cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    /**
     * Returns true if both recipes have the same title.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getTitle().equals(getTitle());
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
        return otherRecipe.getTitle().equals(getTitle())
                && otherRecipe.getDesc().equals(getDesc())
                && otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getSteps().equals(getSteps())
                && otherRecipe.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, desc, ingredients, steps, tags, isStar);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; isStarred: ")
                .append(isStar)
                .append("; Description: ")
                .append(getDesc());

        Set<Ingredient> ingredients = getIngredients();
        if (!ingredients.isEmpty()) {
            builder.append("; Ingredients: ");
            ingredients.forEach(builder::append);
        }

        List<Step> steps = getSteps();
        if (!steps.isEmpty()) {
            builder.append("; Steps: ");
            steps.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
