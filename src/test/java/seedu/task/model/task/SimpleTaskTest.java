package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.BOB;
import static seedu.task.testutil.TypicalTasks.ELLE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.task.model.tag.Tag;
import seedu.task.testutil.SimpleTaskBuilder;

public class SimpleTaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new SimpleTaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedAlice = new SimpleTaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new SimpleTaskBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new SimpleTaskBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new SimpleTaskBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new SimpleTaskBuilder(ALICE).build();
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
        Task editedAlice = new SimpleTaskBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> returns false
        editedAlice = new SimpleTaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new SimpleTaskBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // same effort -> return true
        Task editedElle1 = new SimpleTaskBuilder(ELLE).withEffort(5).build();
        Task editedElle2 = new SimpleTaskBuilder(ELLE).withEffort(5).build();
        assertTrue(editedElle1.equals(editedElle2));

        // different effort -> return false
        editedAlice = new SimpleTaskBuilder(ALICE).withEffort(1).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void compareTo_tags() {
        Tag tagOne = new Tag("Tag1");
        Tag tagTwo = new Tag("Tag2");
        Set<Tag> zeroTag = new HashSet<>();
        Set<Tag> oneTag = new HashSet<>();
        oneTag.add(tagOne);
        Set<Tag> twoTag = new HashSet<>();
        twoTag.add(tagOne);
        twoTag.add(tagTwo);

        Task zeroTagDeadline = new SimpleTask(new Name("zeroTag"), new Description("zeroTag"),
                zeroTag, new Effort(5));
        Task oneTagDeadline = new SimpleTask(new Name("oneTag"), new Description("oneTag"),
                oneTag, new Effort(5));
        Task twoTagDeadline = new SimpleTask(new Name("twoTag"), new Description("twoTag"),
                twoTag, new Effort(5));

        assertEquals(1, oneTagDeadline.compareTo(zeroTagDeadline));
        assertEquals(-1, oneTagDeadline.compareTo(twoTagDeadline));
    }

    @Test
    public void compareTo_name() {
        Task aName = new SimpleTask(new Name("apple"), new Description("apple"),
                new HashSet<>(), new Effort(5));
        Task bName = new SimpleTask(new Name("bucket"), new Description("bucket"),
                new HashSet<>(), new Effort(5));
        Task cName = new SimpleTask(new Name("car"), new Description("car"),
                new HashSet<>(), new Effort(5));

        assertEquals(1, bName.compareTo(aName));
        assertEquals(-1, bName.compareTo(cName));
    }

    @Test
    public void compareTo_all_tasks() {
        Task aName = new SimpleTask(new Name("apple"), new Description("apple"),
                new HashSet<>(), new Effort(5));
        Task bName = new Deadline(new Name("bucket"), new Description("bucket"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Effort(5));
        Task cName = new Event(new Name("car"), new Description("car"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(5));

        assertEquals(1, bName.compareTo(aName));
        assertEquals(-1, bName.compareTo(cName));
        assertEquals(-1, aName.compareTo(cName));
    }
}
