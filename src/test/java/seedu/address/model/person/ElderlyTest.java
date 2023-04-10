package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISK_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.ALICE;
import static seedu.address.testutil.TypicalElderly.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ElderlyBuilder;

public class ElderlyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new ElderlyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, same nric, all other attributes different -> returns true
        Elderly editedAlice = new ElderlyBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withBirthDate(VALID_BIRTH_DATE_BOB).withRiskLevel(VALID_RISK_LEVEL_BOB)
                .withRegion(VALID_REGION_BOB)
                .withTags(VALID_TAG_SINGLE).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, different nric, all other attributes different -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_SINGLE).withNric(VALID_NRIC_BOB)
                .withRegion(VALID_REGION_BOB)
                .withRiskLevel(VALID_RISK_LEVEL_BOB).withBirthDate(VALID_BIRTH_DATE_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns true
        editedAlice = new ElderlyBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new ElderlyBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ElderlyBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // different nric, all other attributes same -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withNric(VALID_NRIC_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // nric differs in case, all other attributes same -> returns true
        editedBob = new ElderlyBuilder(BOB).withNric(VALID_NRIC_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // nric has trailing spaces, all other attributes same -> returns true
        String nricWithTrailingSpaces = VALID_NRIC_BOB + " ";
        editedBob = new ElderlyBuilder(BOB).withName(nricWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new ElderlyBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different person -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Person editedAlice = new ElderlyBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different address -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different age -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withBirthDate(VALID_BIRTH_DATE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different nric -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withNric(VALID_NRIC_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different region -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withRegion(VALID_REGION_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different risklevel -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withRiskLevel(VALID_RISK_LEVEL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different tags -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withTags(VALID_TAG_SINGLE).build();
        assertNotEquals(ALICE, editedAlice);

        // different available dates -> returns false
        editedAlice = new ElderlyBuilder(ALICE).withAvailableDates(VALID_START_DATE_ONE, VALID_END_DATE_ONE).build();
        assertNotEquals(ALICE, editedAlice);
    }
}
