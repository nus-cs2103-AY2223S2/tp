package seedu.dengue.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_ALICE;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_POSTAL_BOB;
import static seedu.dengue.logic.commands.CommandTestUtil.VALID_VARIANT_DENV2_UPPERCASE;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.ALICE;
import static seedu.dengue.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.dengue.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getVariants().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPostal(VALID_POSTAL_BOB)
                .withDate(VALID_DATE_BOB)
                .withAge(VALID_AGE_BOB).withVariants(VALID_VARIANT_DENV2_UPPERCASE).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name same, all other attributes same -> returns true
        Person duplicateAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_ALICE)
                .withAge(VALID_AGE_ALICE)
                .withDate(VALID_DATE_ALICE)
                .withPostal(VALID_POSTAL_ALICE)
                .build();
        assertTrue(ALICE.isSamePerson(duplicateAlice));
    }
    @Test
    public void getCopy() {
        for (int i = 0; i < 5; i++) {
            Person person = new PersonBuilder().buildRandom();
            assertTrue(person.isSamePerson(person.getCopy()));
            assertFalse(person == person.getCopy());
        }
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
        editedAlice = new PersonBuilder(ALICE).withPostal(VALID_POSTAL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different date -> returns false
        editedAlice = new PersonBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAge(VALID_AGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different variants -> returns false
        editedAlice = new PersonBuilder(ALICE).withVariants(VALID_VARIANT_DENV2_UPPERCASE).build();
        assertFalse(ALICE.equals(editedAlice));
    }


}
