package seedu.wife.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.wife.commons.exceptions.IllegalValueException;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * An Immutable Wife that is serializable to JSON format.
 */
@JsonRootName(value = "wife")
class JsonSerializableWife {

    public static final String MESSAGE_DUPLICATE_FOODS = "Food list contains duplicate food(s).";
    public static final String MESSAGE_DUPLICATE_TAGS = "Tag list contains duplicate tag(s).";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFood} with the given foods.
     */
    @JsonCreator
    public JsonSerializableWife(@JsonProperty("foods") List<JsonAdaptedFood> foods) {
        this.foods.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlyFood} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFood}.
     */
    public JsonSerializableWife(ReadOnlyWife source) {
        foods.addAll(source.getFoodList().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts Wife into the model's {@code Wife} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Wife toModelType() throws IllegalValueException {
        Wife wife = new Wife();

        // Add food to WIFE
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (wife.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOODS);
            }
            wife.addFood(food);
        }

        // Add Tags to WIFE
        for (JsonAdaptedTag jsonAdaptedTag : tags) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (wife.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAGS);
            }
            wife.addTag(tag);
        }

        return wife;
    }

}
