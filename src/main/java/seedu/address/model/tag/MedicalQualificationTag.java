package seedu.address.model.tag;

import java.time.LocalDate;

/**
 * Represents medical qualifications that a volunteer might have.
 *
 * @author wz2k
 */
public class MedicalQualificationTag extends Tag{
    /* Level of training attained */
    private SkillLevel qualificationLevel;

    /* Date where qualification becomes invalid */
    private LocalDate expiryDate;

    /**
     * Constructs a {@code MedicalQualification}
     *
     * @param tagName A valid tag name.
     * @param qualificationLevel How qualified it is.
     * @param expiryDate Validity end date.
     */
    public MedicalQualificationTag(String tagName, SkillLevel qualificationLevel, LocalDate expiryDate) {
        super(tagName);
        this.qualificationLevel = qualificationLevel;
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the level of training attained.
     *
     * @return Level of training.
     */
    public SkillLevel getQualificationLevel() {
        return qualificationLevel;
    }

    /**
     * Returns the date where qualification becomes invalid.
     *
     * @return Qualification expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Returns whether the qualification has expired.
     *
     * @return True is the expiry date is over and false otherwise
     */
    public boolean isExpired() {
        LocalDate currentDate = LocalDate.now();

        return currentDate.isAfter(expiryDate);
    }

    /**
     * Changes the skill level of the medical qualification to the given level.
     *
     * @param qualificationLevel The new level.
     */
    public void setQualificationLevel(SkillLevel qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    /**
     * Changes the expiry date of the medical qualification to the given date.
     *
     * @param expiryDate The new end date.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the full details of the medical qualification.
     *
     * @return Full details.
     */
    public String toFullString() {
        String qualificationLevelString = qualificationLevel.name();
        String expiryDateString = expiryDate.toString();
        StringBuilder fullString = new StringBuilder(super.toString()).append(" ")
                .append(qualificationLevelString).append(" ").append(expiryDateString);

        return fullString.toString();
    }
}
