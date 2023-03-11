package bookopedia.model.person;

import static bookopedia.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static bookopedia.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static bookopedia.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static bookopedia.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PARCEL_LAZADA;
import static bookopedia.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static bookopedia.testutil.Assert.assertThrows;
import static bookopedia.testutil.TypicalPersons.ALICE;
import static bookopedia.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bookopedia.model.DeliveryStatus;
import bookopedia.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getParcels().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name and address, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(ALICE.getAddress().toString()).withParcels(VALID_PARCEL_LAZADA).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes different -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_AMY).withParcels(VALID_PARCEL_LAZADA).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different parcels -> returns false
        editedAlice = new PersonBuilder(ALICE).withParcels(VALID_PARCEL_LAZADA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different delivery status -> returns false
        editedAlice = new PersonBuilder(ALICE).withDeliveryStatus(DeliveryStatus.OTW).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
