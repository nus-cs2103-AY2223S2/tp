package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.Reroll;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

/**
 * An Immutable Reroll that is serializable to JSON format.
 */
@JsonRootName(value = "reroll")
public class JsonSerializableReroll {
    private final List<JsonAdaptedMob> mobs = new ArrayList<>();
    private final List<JsonAdaptedCharacter> characters = new ArrayList<>();
    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableReroll} with the given entities.
     */
    @JsonCreator
    public JsonSerializableReroll(@JsonProperty("mobs") List<JsonAdaptedMob> mobs,
                                  @JsonProperty("characters") List<JsonAdaptedCharacter> characters,
                                  @JsonProperty("items") List<JsonAdaptedItem> items) {
        this.mobs.addAll(mobs);
        this.characters.addAll(characters);
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code ReadOnlyReroll} into this class for Jackson use.
     */
    public JsonSerializableReroll(ReadOnlyReroll source) {
        mobs.addAll(source.getEntities().getMobList().stream().map(x -> (Mob) x)
                .map(JsonAdaptedMob::new).collect(Collectors.toList()));
        characters.addAll(source.getEntities().getCharList().stream()
                .map(x -> (Character) x).map(JsonAdaptedCharacter::new).collect(Collectors.toList()));
        items.addAll(source.getEntities().getItemList().stream().map(x -> (Item) x)
                .map(JsonAdaptedItem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Reroll object into the model's {@code Reroll} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Reroll toModelType() throws IllegalValueException {
        Reroll reroll = new Reroll();
        // Add all mobs
        for (JsonAdaptedMob jsonMob : mobs) {
            reroll.addEntity(jsonMob.toModelType());
        }
        // Add all characters
        for (JsonAdaptedCharacter jsonChar : characters) {
            reroll.addEntity(jsonChar.toModelType());
        }

        // Add all items
        for (JsonAdaptedItem jsonItem : items) {
            reroll.addEntity(jsonItem.toModelType());
        }

        return reroll;
    }

}
