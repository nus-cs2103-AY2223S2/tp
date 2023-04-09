package seedu.address.storage;

import static seedu.address.model.entity.Character.CharacterBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Progression;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Character}
 */
public class JsonAdaptedCharacter {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Character's %s field is missing!";

    private final String name;
    private final JsonAdaptedStats stats;
    private final JsonAdaptedInventory inventory;
    private final JsonAdaptedProgression progression;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCharacter} with the given character details.
     */
    @JsonCreator
    JsonAdaptedCharacter(@JsonProperty("name") String name, @JsonProperty("stats") JsonAdaptedStats stats,
                         @JsonProperty("inventory") JsonAdaptedInventory inventory,
                         @JsonProperty("progression") JsonAdaptedProgression progression,
                         @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.stats = stats;
        this.progression = progression;
        this.inventory = inventory;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Character} into this class for Jackson use.
     */
    public JsonAdaptedCharacter(Character source) {
        name = source.getName().fullName;
        stats = new JsonAdaptedStats(source.getStats());
        inventory = new JsonAdaptedInventory(source.getInventory());
        progression = new JsonAdaptedProgression(source.getProgression());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Character object into the model's {@code Character} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted character.
     */
    public Character toModelType() throws IllegalValueException {
        Stats stat = stats.toModalType();
        Inventory inventory = this.inventory.toModelType();
        Progression progression = this.progression.toModalType();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);

        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(tags);
        CharacterBuilder builder = new CharacterBuilder(modelName)
                .setStats(stat)
                .setProgression(progression)
                .setInventory(inventory)
                .setTags(modelTags);

        return builder.build();
    }

}
