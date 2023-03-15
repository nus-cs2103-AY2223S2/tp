package seedu.address.experimental.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Stats;
/***/
public class JsonAdaptedStats {

    private final int strength;
    private final int dexterity;
    private final int intelligence;
    /***/
    @JsonCreator
    public JsonAdaptedStats(@JsonProperty("strength") int strength, @JsonProperty("dexterity") int dexterity,
                            @JsonProperty("intelligence") int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }
    /***/
    public JsonAdaptedStats(Stats stats) {
        this.strength = stats.getStrength();
        this.dexterity = stats.getDexterity();
        this.intelligence = stats.getIntelligence();
    }
    /***/
    public Stats toModalType() throws IllegalValueException {
        return new Stats(strength, dexterity, intelligence);
    }

}
