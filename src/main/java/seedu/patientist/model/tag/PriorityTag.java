package seedu.patientist.model.tag;

import static seedu.patientist.commons.util.AppUtil.checkArgument;

/**
 * Represents the severity of a patient in the patientist.
 * Severity is either HIGH, MEDIUM, or LOW.
 */
public class PriorityTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "SeverityTag should be either HIGH, MEDIUM, or LOW";

    /**
     * Constructs a {@code SeverityTag}.
     * This tag is used to show severity of a patient.
     * @param tagName A valid tag name.
     */
    public PriorityTag(String tagName) {
        super(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidTagName(String test) {
        return test.equals("HIGH") || test.equals("MEDIUM") || test.equals("LOW");
    }
}
