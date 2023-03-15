package seedu.address.experimental.storage;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Stats;
import seedu.address.model.entity.Name;

/***/
public class JsonAdaptedMob {
    private final String name;
    private final JsonAdaptedStats stats;
    private final int challengeRating;
    private final boolean isLegendary;
    /***/
    @JsonCreator
    JsonAdaptedMob(@JsonProperty("name") String name, @JsonProperty("stats") JsonAdaptedStats stats,
                   @JsonProperty("challengeRating") int challengeRating,
                   @JsonProperty("isLegendary") boolean isLegendary) {
        this.name = name;
        this.stats = stats;
        this.challengeRating = challengeRating;
        this.isLegendary = isLegendary;
    }
    /***/
    public JsonAdaptedMob(Mob source) {
        name = source.getName().fullName;
        stats = new JsonAdaptedStats(new Stats(0, 0, 0));
        this.challengeRating = source.getChallengeRating();
        this.isLegendary = source.getLegendaryStatus();
    }
    /***/
    public Mob toModelType() throws IllegalValueException {
        // dont care
        Stats stat = stats.toModalType();
        return new Mob(new Name(name), stat, challengeRating, isLegendary, new HashSet<>());
    }
}
