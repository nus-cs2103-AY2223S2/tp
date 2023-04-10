package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalSubtasks.ALICE_HOMEWORK;
import static seedu.task.testutil.TypicalSubtasks.AMY_LAB;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.SubtaskBuilder;

public class SubtaskTest {


    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ALICE_HOMEWORK.isSameTask(ALICE_HOMEWORK));

        // null -> returns false
        assertFalse(ALICE_HOMEWORK.isSameTask(null));

        // same name, all other attributes different -> returns true
        Subtask editedAliceHomework = new SubtaskBuilder().withName("Homework").withDescription("CS2103T").build();
        assertTrue(ALICE_HOMEWORK.isSameTask(editedAliceHomework));

        // different name, all other attributes same -> returns false
        editedAliceHomework = new SubtaskBuilder().withName("AMY").build();
        assertFalse(ALICE_HOMEWORK.isSameTask(editedAliceHomework));

        // name differs in case, all other attributes same -> returns true
        Subtask editedAlice = new SubtaskBuilder().withName("Homework".toLowerCase()).build();
        assertTrue(ALICE_HOMEWORK.isSameTask(editedAlice));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "Homework" + " ";
        editedAlice = new SubtaskBuilder().withName(nameWithTrailingSpaces).build();
        assertFalse(ALICE_HOMEWORK.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Subtask aliceHomeworkCopy = new SubtaskBuilder(ALICE_HOMEWORK).build();
        assertTrue(ALICE_HOMEWORK.equals(aliceHomeworkCopy));

        // same object -> returns true
        assertTrue(ALICE_HOMEWORK.equals(ALICE_HOMEWORK));

        // null -> returns false
        assertFalse(ALICE_HOMEWORK.equals(null));

        // different type -> returns false
        assertFalse(ALICE_HOMEWORK.equals(5));

        // different task -> returns false
        assertFalse(ALICE_HOMEWORK.equals(AMY_LAB));

        // different name -> returns false
        Subtask editedAliceHomework = new SubtaskBuilder(ALICE_HOMEWORK)
            .withName(AMY_LAB.getName().fullName).build();
        assertFalse(ALICE_HOMEWORK.equals(editedAliceHomework));

        // different description -> returns false
        editedAliceHomework = new SubtaskBuilder(ALICE_HOMEWORK)
            .withDescription(AMY_LAB.getDescription().value).build();
        assertFalse(ALICE_HOMEWORK.equals(editedAliceHomework));



    }


}
