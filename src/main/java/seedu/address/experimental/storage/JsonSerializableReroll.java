package seedu.address.experimental.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.Reroll;

/***/
@JsonRootName(value = "reroll")
public class JsonSerializableReroll {
    private final List<JsonAdaptedMob> mobs = new ArrayList<>();
    private final List<JsonAdaptedCharacter> characters = new ArrayList<>();
    /***/
    @JsonCreator
    public JsonSerializableReroll(@JsonProperty("mobs") List<JsonAdaptedMob> mobs,
                                  @JsonProperty("characters") List<JsonAdaptedCharacter> characters) {
        this.mobs.addAll(mobs);
        this.characters.addAll(characters);
    }
    /***/
    public JsonSerializableReroll(ReadOnlyReroll source) {
        mobs.addAll(source.getMobs().getEntityList().stream().map(JsonAdaptedMob::new).collect(Collectors.toList()));
        characters.addAll(source.getCharacters()
                .getEntityList().stream().map(JsonAdaptedCharacter::new).collect(Collectors.toList()));
    }
    /***/
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
        return reroll;
    }

}
