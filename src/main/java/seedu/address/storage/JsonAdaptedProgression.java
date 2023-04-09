package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Progression;

/**
 * Jackson-friendly version of {@link Progression}
 */
public class JsonAdaptedProgression {

    private final int level;
    private final int xp;

    /**
     * Constructs a {@code JsonAdaptedProgression} with the given progression details.
     */
    @JsonCreator
    public JsonAdaptedProgression(@JsonProperty("level") int level, @JsonProperty("xp") int xp) {
        this.level = level;
        this.xp = xp;
    }

    /**
     * Converts a given {@code Stats} into this class for Jackson use.
     */
    public JsonAdaptedProgression(Progression progression) {
        this.level = progression.getLevel();
        this.xp = progression.getXP();
    }

    /**
     * Converts this Jackson-friendly adapted Stats object into the model's {@code Progression} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted progression.
     */
    public Progression toModalType() throws IllegalValueException {
        return new Progression(level, xp);
    }

}
