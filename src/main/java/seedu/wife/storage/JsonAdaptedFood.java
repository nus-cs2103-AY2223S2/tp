package seedu.wife.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.wife.commons.exceptions.IllegalValueException;
import seedu.wife.model.food.ExpiryDate;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;
import seedu.wife.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Food}.
 */
class JsonAdaptedFood {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Food's %s field is missing!";

    private final String name;
    private final String unit;
    private final String quantity;
    private final String expiryDate;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFood} with the given food details.
     */
    @JsonCreator
    public JsonAdaptedFood(@JsonProperty("name") String name, @JsonProperty("unit") String unit,
                             @JsonProperty("quantity") String quantity, @JsonProperty("expiryDate") String expiryDate,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Food} into this class for Jackson use.
     */
    public JsonAdaptedFood(Food source) {
        name = source.getName().toString();
        unit = source.getUnit().toString();
        quantity = source.getQuantity().toString();
        expiryDate = source.getExpiryDate().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted food object into the model's {@code Food} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted food.
     */
    public Food toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValid(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (unit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Unit.class.getSimpleName()));
        }
        if (!Unit.isValid(unit)) {
            throw new IllegalValueException(Unit.MESSAGE_CONSTRAINTS);
        }
        final Unit modelUnit = new Unit(unit);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValid(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (expiryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ExpiryDate.class.getSimpleName()));
        }
        if (!ExpiryDate.isValid(expiryDate)) {
            throw new IllegalValueException(ExpiryDate.FORMAT_MESSAGE_CONSTRAINTS);
        }
        final ExpiryDate modelExpiryDate = new ExpiryDate(expiryDate);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Food(modelName, modelUnit, modelQuantity, modelExpiryDate, modelTags);
    }
}
