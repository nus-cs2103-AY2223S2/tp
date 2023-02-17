package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Task editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TaskBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new TaskBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different task -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
