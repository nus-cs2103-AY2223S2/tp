package seedu.recipe.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.recipe.logic.parser.ParserUtil;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {

    public static final String DEFAULT_TITLE = "A Recipe";
    public static final String DEFAULT_DESCRIPTION = "A Description";
    public static final List<Ingredient> DEFAULT_INGREDIENTS = Arrays.asList(
            new Ingredient("eggs", 2.0, "unit", 0.9),
            new Ingredient("flour", 3.5, "cup", 0.1));
    public static final List<Step> DEFAULT_STEPS = Arrays.asList(
            new Step("step 1"), new Step("step 2"));
    public static final List<Tag> DEFAULT_TAGS = Arrays.asList(
            new Tag("easy"), new Tag("snack"));

    private Title title;
    private Description desc;
    private Set<Ingredient> ingredients;
    private List<Step> steps;
    private Set<Tag> tags;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        title = new Title(DEFAULT_TITLE);
        desc = new Description(DEFAULT_DESCRIPTION);
        ingredients = new HashSet<Ingredient>(DEFAULT_INGREDIENTS);
        steps = new ArrayList<Step>(DEFAULT_STEPS);
        tags = new HashSet<Tag>(DEFAULT_TAGS);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        title = recipeToCopy.getTitle();
        desc = recipeToCopy.getDesc();
        ingredients = recipeToCopy.getIngredients();
        steps = recipeToCopy.getSteps();
        tags = recipeToCopy.getTags();
    }

    /**
     * Sets the {@code Title} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withDesc(String desc) {
        this.desc = new Description(desc);
        return this;
    }

    /**
     * Sets the {@code Ingredients} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withIngredients(String... ingredients) {
        List<String> list = Arrays.asList(ingredients);
        Collection<String> collectionOfIngredients = new ArrayList<>(list);
        try {
            this.ingredients = ParserUtil.parseIngredients(collectionOfIngredients);
        } catch (ParseException e) {
            return null;
        }
        return this;
    }

    /**
     * Sets the {@code Steps} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withSteps(String... steps) {
        List<Step> stepList = Stream.of(steps).map(Step::new).collect(Collectors.toList());
        this.steps = stepList;
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        this.tags = tagSet;
        return this;
    }

    public Recipe build() {
        return new Recipe(title, desc, ingredients, steps, tags);
    }

}
