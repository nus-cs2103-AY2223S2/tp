package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link MedicalQualificationTag}.
 */
public class JsonAdaptedMedicalTag implements JsonSerializable<MedicalQualificationTag> {
    private final String skill;
    private final String level;

    /**
     * Constructs a {@code JsonAdaptedMedicalTag} with the given medical tag details.
     */
    @JsonCreator
    public JsonAdaptedMedicalTag(@JsonProperty("skill") String skill, @JsonProperty("level") String level) {
        this.skill = skill;
        this.level = level;
    }

    /**
     * Converts a given {@code MedicalQualificationTag} into this class for Jackson use.
     */
    public JsonAdaptedMedicalTag(MedicalQualificationTag source) {
        this.skill = source.tagName;
        this.level = source.getQualificationLevel();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code MedicalQualificationTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical tag.
     */
    public MedicalQualificationTag toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        if (!Tag.isValidTagName(skill) || !MedicalQualificationTag.isValidQualification(level)) {
            throw new IllegalValueException(MedicalQualificationTag.MESSAGE_CONSTRAINTS);
        }
        return new MedicalQualificationTag(skill, level);
    }
}
