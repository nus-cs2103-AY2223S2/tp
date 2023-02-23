package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmergencyContactTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(null, null));
    }

    @Test
    public void isValidRelationship() {
        // null
        assertThrows(NullPointerException.class, () -> new DrugAllergy(null));

        // blank description
        assertFalse(EmergencyContact.isValidRelationship("")); // empty string
        assertFalse(EmergencyContact.isValidRelationship(" ")); // spaces only

        // invalid relationship
        assertFalse(EmergencyContact.isValidRelationship("With empty blank"));
        assertFalse(EmergencyContact.isValidRelationship("WithSpecialCharacter["));

        // valid relationship
        assertTrue(EmergencyContact.isValidRelationship("mother"));
        assertTrue(EmergencyContact.isValidRelationship("aunt"));
    }
}
