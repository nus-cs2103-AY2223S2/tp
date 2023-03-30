package seedu.address.experimental.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Item;

/**
 * Jackson friendly version of {@link Inventory}
 */
public class JsonAdaptedInventory {
    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInventory} with the given inventory
     */
    @JsonCreator
    JsonAdaptedInventory(@JsonProperty("items") List<JsonAdaptedItem> items) {
        if (items != null) {
            this.items.addAll(items);
        }
    }

    /**
     * Converts a given {@code Inventory} into this class for Jackson use
     */
    public JsonAdaptedInventory(Inventory source) {
        items.addAll(source.getItems().stream()
                .map(JsonAdaptedItem::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Inventory object into the model's {@code Inventory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted inventory.
     */
    public Inventory toModelType() throws IllegalValueException {
        final List<Item> items = new ArrayList<>();
        for (JsonAdaptedItem item : this.items) {
            items.add(item.toModelType());
        }
        return new Inventory(items);
    }




}
