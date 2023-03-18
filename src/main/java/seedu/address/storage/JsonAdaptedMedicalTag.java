package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

public class JsonAdaptedMedicalTag implements JsonSerializable<MedicalQualificationTag> {
    private final String skill;
    private final String level;

    @JsonCreator
    public JsonAdaptedMedicalTag(String skill, String level) {
        this.skill = skill;
        this.level = level;
    }

    public JsonAdaptedMedicalTag(MedicalQualificationTag source) {
        this.skill = source.tagName;
        this.level = source.getQualificationLevel();
    }

    public String getSkill() {
        return skill;
    }

    public String getLevel() {
        return level;
    }

    public MedicalQualificationTag toModelType(FriendlyLink friendlyLink) throws IllegalValueException {
        if (!Tag.isValidTagName(skill) || MedicalQualificationTag.isValidQualification(level)) {
            throw new IllegalValueException(MedicalQualificationTag.MESSAGE_CONSTRAINTS);
        }
        return new MedicalQualificationTag(skill, level);
    }
}
