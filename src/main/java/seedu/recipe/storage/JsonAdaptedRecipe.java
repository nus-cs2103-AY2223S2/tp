package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Recipe}.
 */
@JsonPropertyOrder({
        "name",
        "portion",
        "duration",
        "tags",
        "ingredients",
        "steps"
})
class JsonAdaptedRecipe {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    @JsonProperty("name")
    private final JsonAdaptedName name;

    @JsonProperty("portion")
    private final Optional<JsonAdaptedRecipePortion> portion;

    @JsonProperty("duration")
    private final Optional<JsonAdaptedRecipeDuration> duration;

    @JsonProperty("tags")
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    @JsonProperty("ingredients")
    private final List<JsonAdaptedIngredient> ingredients = new ArrayList<>();

    @JsonProperty("steps")
    private final List<JsonAdaptedStep> steps = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(
            @JsonProperty("name") JsonAdaptedName name,
            @JsonProperty("portion") Optional<JsonAdaptedRecipePortion> portion,
            @JsonProperty("duration") Optional<JsonAdaptedRecipeDuration> duration,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("ingredient") List<JsonAdaptedIngredient> ingredients,
            @JsonProperty("steps") List<JsonAdaptedStep> steps) {
        this.name = name;
        this.portion = portion;
        this.duration = duration;

        if (tags != null) {
            this.tags.addAll(tags);
        }

        if (ingredients != null) {
            this.ingredients.addAll(ingredients);
        }

        if (steps != null) {
            this.steps.addAll(steps);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        name = new JsonAdaptedName(source.getName());
        portion = Optional.ofNullable(source.getPortionNullable()).map(JsonAdaptedRecipePortion::new);
        duration = Optional.ofNullable(source.getDurationNullable()).map(JsonAdaptedRecipeDuration::new);

        tags.addAll(
                source.getTags().stream()
                        .map(JsonAdaptedTag::new)
                        .collect(Collectors.toList())
                   );

        ingredients.addAll(
                source.getIngredients().stream()
                        .map(JsonAdaptedIngredient::new)
                        .collect(Collectors.toList())
        );
        steps.addAll(
                source.getSteps().stream()
                        .map(JsonAdaptedStep::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     * Remember only name field is required, and the rest are optional.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
//        if (name == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
//        }
//        if (!Name.isValidName(name)) {
//            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
//        }
//        final Name modelName = new Name(name);


        Recipe res = new Recipe(name.toModelType());

        // set portion and duration
        res.setPortion(portion.flatMap(JsonAdaptedRecipePortion::toModelTypeOptional).orElse(null));
        res.setDuration(duration.flatMap(JsonAdaptedRecipeDuration::toModelTypeOptional).orElse(null));

        List<Optional<Tag>> outTags = tags.stream()
                .map(JsonAdaptedTag::toModelTypeOptional)
                .collect(Collectors.toList());
        List<Tag> t = new ArrayList<>();
        outTags.forEach(i -> i.ifPresent(t::add));
        res.setTags(t.toArray(Tag[]::new));

        List<Optional<Ingredient>> outIngredients = ingredients.stream()
                .map(JsonAdaptedIngredient::toModelTypeOptional)
                .collect(Collectors.toList());
        List<Ingredient> i = new ArrayList<>();
        outIngredients.forEach(ingredient -> ingredient.ifPresent(i::add));
        res.setIngredients(i.toArray(Ingredient[]::new));

        List<Optional<Step>> outSteps = steps.stream()
                .map(JsonAdaptedStep::toModelTypeOptional)
                .collect(Collectors.toList());
        List<Step> s = new ArrayList<>();
        outSteps.forEach(step -> step.ifPresent(s::add));
        res.setSteps(s.toArray(Step[]::new));

        return res;
    }

}
