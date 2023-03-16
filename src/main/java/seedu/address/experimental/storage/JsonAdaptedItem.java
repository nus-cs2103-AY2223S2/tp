package seedu.address.experimental.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Name;
import seedu.address.model.tag.Tag;

/***/
public class JsonAdaptedItem {
    private final String name;
    private final int cost;
    private final float weight;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /***/
    @JsonCreator
    JsonAdaptedItem(@JsonProperty("name") String name, @JsonProperty("cost") int cost,
                    @JsonProperty("weight") float weight,
                    @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /***/
    public JsonAdaptedItem(Item source) {
        name = source.getName().fullName;
        cost = source.getCost();
        weight = source.getWeight();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /***/
    public Item toModelType() throws IllegalValueException {
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(tags);
        return new Item(new Name(name), cost, weight, modelTags);
    }

}
