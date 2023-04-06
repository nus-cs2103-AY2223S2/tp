package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Stats;

/**
 * Jackson-friendly version of {@link Stats}
 */
public class JsonAdaptedStats {

    private final int strength;
    private final int dexterity;
    private final int intelligence;

    /**
     * Constructs a {@code JsonAdaptedStats} with the given stats details.
     */
    @JsonCreator
    public JsonAdaptedStats(@JsonProperty("strength") int strength, @JsonProperty("dexterity") int dexterity,
                            @JsonProperty("intelligence") int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    /**
     * Converts a given {@code Stats} into this class for Jackson use.
     */
    public JsonAdaptedStats(Stats stats) {
        this.strength = stats.getStrength();
        this.dexterity = stats.getDexterity();
        this.intelligence = stats.getIntelligence();
    }

    /**
     * Converts this Jackson-friendly adapted Stats object into the model's {@code Stats} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stats.
     */
    public Stats toModalType() throws IllegalValueException {
        return new Stats(strength, dexterity, intelligence);
    }

}
