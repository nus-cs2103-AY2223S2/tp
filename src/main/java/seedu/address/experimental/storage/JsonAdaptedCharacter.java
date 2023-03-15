package seedu.address.experimental.storage;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Stats;
import seedu.address.model.entity.Name;

/***/
public class JsonAdaptedCharacter {
    private final String name;
    private final JsonAdaptedStats stats;
    private final int level;
    private final int xp;

    @JsonCreator
    JsonAdaptedCharacter(@JsonProperty("name") String name, @JsonProperty("stats") JsonAdaptedStats stats,
                         @JsonProperty("level") int level, @JsonProperty("xp") int xp) {
        this.name = name;
        this.stats = stats;
        this.level = level;
        this.xp = xp;
    }
    /***/
    public JsonAdaptedCharacter(Character source) {
        name = source.getName().fullName;
        stats = new JsonAdaptedStats(new Stats(0, 0, 0));
        this.level = source.getLevel();
        this.xp = source.getXP();
    }
    /***/
    public Character toModelType() throws IllegalValueException {
        // dont care about error...
        Stats stat = stats.toModalType();
        return new Character(new Name(name), stat, level, xp, new HashSet<>());
    }

}
