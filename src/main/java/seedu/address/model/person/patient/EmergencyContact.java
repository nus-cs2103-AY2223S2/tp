package seedu.address.model.person.patient;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;

/**
 * Represents a patient's emergency contact in the patient record
 */
public class EmergencyContact {
    public static final String MESSAGE_CONSTRAINTS_FOR_EMERGENCY_CONTACT_PEOPLE =
            "Emergency contact should contain the person's name and phone number, it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_FOR_RELATIONSHIP =
            "Relationship should only contain characters, and it should not be blank\"";

    private static final String VALIDATION_REGEX_FOR_RELATIONSHIP = "\\p{Alnum}+";
    private final Person emergencyContact;
    private final String relationship;

    /**
     * Constructs a {@code EmergencyContact}.
     *
     * @param emergencyContact the person that is the first person medical personnel will get
     *     in touch with in an emergency.
     * @param relationship the relationship between the patient and his/her emergency contact
     */
    public EmergencyContact(Person emergencyContact, String relationship) {
        requireAllNonNull(emergencyContact, relationship);
        checkArgument(isValidEmergencyContact(emergencyContact), MESSAGE_CONSTRAINTS_FOR_EMERGENCY_CONTACT_PEOPLE);
        checkArgument(isValidRelationship(relationship), MESSAGE_CONSTRAINTS_FOR_RELATIONSHIP);
        this.emergencyContact = emergencyContact;
        this.relationship = relationship;
    }

    /**
     * Returns true if a given string is either female or male.
     */
    public static boolean isValidEmergencyContact(Person test) {
        return test.getName() != null && test.getPhone() != null;
    }

    /**
     * Returns true if a given string is a valid relationship.
     */
    public static boolean isValidRelationship(String test) {
        return test.matches(VALIDATION_REGEX_FOR_RELATIONSHIP);
    }

    @Override
    public String toString() {
        return String.format("Information of emergency contact person: /n%s "
                        + "/nThe relationship between the patient and the emergency contact is %s",
                emergencyContact.toString(), relationship);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmergencyContact // instanceof handles nulls
                && emergencyContact.equals(((EmergencyContact) other).emergencyContact) // state check
                && relationship.equals(((EmergencyContact) other).relationship));
    }

}
