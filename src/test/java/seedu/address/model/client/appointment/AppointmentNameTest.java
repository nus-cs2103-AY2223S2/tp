package seedu.address.model.client.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.testutil.ClientBuilder;

public class AppointmentNameTest {


    private final AppointmentName DISC = new AppointmentName(VALID_APPOINTMENT_NAME);
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> AppointmentName.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        AppointmentName discCopy = new AppointmentName(VALID_APPOINTMENT_NAME);
        AppointmentName empty = new AppointmentName();
        AppointmentName emptyCopy = new AppointmentName();
        Client alice = new ClientBuilder(ALICE).build();
        // same values -> returns true
        assertEquals(DISC, discCopy);

        // same object --> returns true
        assertEquals(DISC, DISC);

        // null -> returns false
        assertNotEquals(null, DISC);

        // different object -> returns false
        assertNotEquals(alice, DISC);

       // empty appointment -> returns true
        assertEquals(empty, emptyCopy);

       // empty appointment with a non-empty appointment -> returns false
       assertNotEquals(empty, DISC);
    }

    @Test
    void testHashCode() {
        AppointmentName discCopy = new AppointmentName(VALID_APPOINTMENT_NAME);
        assertEquals(discCopy.hashCode(), DISC.hashCode());
    }
}
