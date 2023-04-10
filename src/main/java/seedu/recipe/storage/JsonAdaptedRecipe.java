package seedu.recipe.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Recipe}.
 */
class JsonAdaptedRecipe {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final String title;
    private final String desc;

    // Data fields
    private final Set<JsonAdaptedIngredient> ingredients = new HashSet<>();
    private final List<JsonAdaptedStep> steps = new ArrayList<>();
    private final Set<JsonAdaptedTag> tags = new HashSet<>();

    private final boolean isStar;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("title") String title,
                             @JsonProperty("desc") String desc,
                             @JsonProperty("ingredients") Set<JsonAdaptedIngredient> ingredients,
                             @JsonProperty("steps") List<JsonAdaptedStep> steps,
                             @JsonProperty("tags") Set<JsonAdaptedTag> tags,
                             @JsonProperty("isStar") boolean isStar) {
        this.title = title;
        this.desc = desc;
        if (ingredients != null) {
            this.ingredients.addAll(ingredients);
        }
        if (steps != null) {
            this.steps.addAll(steps);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.isStar = isStar;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        title = source.getTitle().title;
        desc = source.getDesc().description;
        ingredients.addAll(source.getIngredients().stream()
                      .map(JsonAdaptedIngredient::new)
                      .collect(Collectors.toList()));
        steps.addAll(source.getSteps().stream()
                .map(JsonAdaptedStep::new)
                .collect(Collectors.toList()));

        tags.addAll(source.getTags().stream()
                    .map(JsonAdaptedTag::new)
                    .collect(Collectors.toList()));
        isStar = source.isStarred();
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        final List<Ingredient> recipeIngredients = new ArrayList<>();
        for (JsonAdaptedIngredient ingredient : ingredients) {
            recipeIngredients.add(ingredient.toModelType());
        }
        final List<Step> modelSteps = new ArrayList<>();
        for (JsonAdaptedStep step : steps) {
            modelSteps.add(step.toModelType());
        }

        final List<Tag> recipeTags = new ArrayList<>();
        for (JsonAdaptedTag tag: tags) {
            recipeTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (desc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDesc(desc)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDesc = new Description(desc);
        final Set<Ingredient> modelIngredients = new HashSet<>(recipeIngredients);
        final Set<Tag> modelTags = new HashSet<>(recipeTags);
        final boolean modelIsStar = isStar;
        return new Recipe(modelTitle, modelDesc, modelIngredients, modelSteps, modelTags, modelIsStar);
    }

}
