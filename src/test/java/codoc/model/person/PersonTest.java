package codoc.model.person;

import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_AMY;
import static codoc.logic.commands.CommandTestUtil.VALID_LINKEDIN_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static codoc.testutil.Assert.assertThrows;
import static codoc.testutil.TypicalPersons.ALICE;
import static codoc.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import codoc.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getSkills().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same email, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withGithub(VALID_GITHUB_AMY).withEmail(VALID_EMAIL_BOB)
                .withLinkedin(VALID_LINKEDIN_AMY).withSkills(VALID_SKILL_JAVA).build();
        assertTrue(BOB.isSamePerson(editedAlice));

        // different email, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // email differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_BOB.toUpperCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        //        String emailWithTrailingSpaces = VALID_EMAIL_BOB + " ";
        //        editedBob = new PersonBuilder(BOB).withEmail(emailWithTrailingSpaces).build();
        //        assertTrue(BOB.isSamePerson(editedBob));
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

        // different github -> returns false
        editedAlice = new PersonBuilder(ALICE).withGithub(VALID_GITHUB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different linkedin -> returns false
        editedAlice = new PersonBuilder(ALICE).withLinkedin(VALID_LINKEDIN_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different skills -> returns false
        editedAlice = new PersonBuilder(ALICE).withSkills(VALID_SKILL_JAVA).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
