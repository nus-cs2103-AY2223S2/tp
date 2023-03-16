package seedu.address.experimental.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Stats;
import seedu.address.model.tag.Tag;

/***/
public class JsonAdaptedCharacter {
    private final String name;
    private final JsonAdaptedStats stats;
    private final int level;
    private final int xp;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    @JsonCreator
    JsonAdaptedCharacter(@JsonProperty("name") String name, @JsonProperty("stats") JsonAdaptedStats stats,
                         @JsonProperty("level") int level, @JsonProperty("xp") int xp,
                         @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.stats = stats;
        this.level = level;
        this.xp = xp;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }
    /***/
    public JsonAdaptedCharacter(Character source) {
        name = source.getName().fullName;
        stats = new JsonAdaptedStats(source.getStats());
        level = source.getLevel();
        xp = source.getXP();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }
    /***/
    public Character toModelType() throws IllegalValueException {
        // dont care about error...
        Stats stat = stats.toModalType();
        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(tags);
        return new Character(new Name(name), stat, level, xp, modelTags);
    }

}
