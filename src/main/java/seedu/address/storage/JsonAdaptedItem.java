package seedu.address.storage;

import static seedu.address.model.entity.Item.ItemBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Cost;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Weight;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Item}
 */
public class JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String name;
    private final int cost;
    private final double weight;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    JsonAdaptedItem(@JsonProperty("name") String name, @JsonProperty("cost") int cost,
                    @JsonProperty("weight") double weight,
                    @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        name = source.getName().fullName;
        cost = source.getCost().getGoldCost();
        weight = source.getWeight().getWeight();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);
        final Cost modelCost = new Cost(cost);
        final Weight modelWeight = new Weight(weight);

        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(tags);

        ItemBuilder builder = new ItemBuilder(modelName)
                .setCost(modelCost)
                .setWeight(modelWeight)
                .setTags(modelTags);

        return builder.build();
    }

}
