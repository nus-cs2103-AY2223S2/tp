package seedu.address.model.tag;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents medical qualifications that a volunteer might have.
 *
 * @author wz2k
 */
public class MedicalQualificationTag extends Tag {
    public static final String MESSAGE_CONSTRAINTS =
            "Medical qualification must be a skill, followed by a skill-level. "
            + "format: [Skill] [Level]";
    public static final String MESSAGE_CONSTRAINTS_SKILL =
            "SkillLevel should only contain 3 types of values, "
            + "basic, intermediate, advanced";

    /**
     * Represents the different skill level a person can have.
     */
    public enum SkillLevel {
        BASIC,
        INTERMEDIATE,
        ADVANCED
    }

    /* Level of training attained */
    private SkillLevel qualificationLevel;

    /* Date where qualification becomes invalid */
    // private LocalDate expiryDate;

    /**
     * Constructs a {@code MedicalQualification}
     * Use "Medical" as the common prefix for medical tags.
     * @param qualificationLevel How qualified it is.
     */
    public MedicalQualificationTag(String skill, String qualificationLevel) {
        super(skill);
        this.qualificationLevel = SkillLevel
                .valueOf(qualificationLevel.toUpperCase());
    }

//    public MedicalQualificationTag(String tagName, SkillLevel qualificationLevel, LocalDate expiryDate) {
//        super(tagName);
//        this.qualificationLevel = qualificationLevel;
//        this.expiryDate = expiryDate;
//    }

    /**
     * Returns the level of training attained.
     *
     * @return Level of training.
     */
    public String getQualificationLevel() {
        return qualificationLevel.name();
    }

    /**
     * Changes the skill level of the medical qualification to the given level.
     *
     * @param qualificationLevel The new level.
     */
    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = SkillLevel.valueOf(qualificationLevel);
    }

    public static boolean isValidQualification(String qualification) {
        if (qualification != null) {
            return Arrays.stream(SkillLevel.values())
                    .anyMatch(r -> r.toString().equals(qualification.toUpperCase()));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MedicalQualificationTag
                && tagName.equals(((MedicalQualificationTag) other).tagName)
                && qualificationLevel.equals(((MedicalQualificationTag) other).qualificationLevel)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, qualificationLevel);
    }

    /**
     * Returns the full details of the medical qualification.
     *
     * @return Full details.
     */
    public String toFullString() {
        String qualificationLevelString = qualificationLevel.name();

        return super.toString() + " " + qualificationLevelString;
    }
}
