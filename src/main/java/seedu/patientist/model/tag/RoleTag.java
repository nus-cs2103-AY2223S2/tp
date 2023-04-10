package seedu.patientist.model.tag;

import static seedu.patientist.commons.util.AppUtil.checkArgument;

/**
 * Represents the role of a person in the patientist.
 * Staff have a RoleTag named "Staff", Patient have a RoleTag named "Patient".
 */
public class RoleTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Role Tag names should be either Patient or Staff";
    public static final String PATIENT_TAG_NAME = "Patient";
    public static final String STAFF_TAG_NAME = "Staff";

    /**
     * Constructs a {@code RoleTag}.
     * This tag is used to differentiate between Patient and Staff
     * @param tagName A valid tag name.
     */
    public RoleTag(String tagName) {
        super(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    public static boolean isValidTagName(String test) {
        return test.equals(PATIENT_TAG_NAME) || test.equals(STAFF_TAG_NAME);
    }

    public boolean isPatientTag() {
        return this.tagName.equals(PATIENT_TAG_NAME);
    }

    public boolean isStaffTag() {
        return this.tagName.equals(STAFF_TAG_NAME);
    }
}
