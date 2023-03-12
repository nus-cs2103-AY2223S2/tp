package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDeadlines.ASSIGNMENT;
import static seedu.task.testutil.TypicalDeadlines.RETURN_BOOK;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.task.model.tag.Tag;
import seedu.task.testutil.DeadlineBuilder;

public class DeadlineTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new DeadlineBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameDeadline() {
        // same object -> returns true
        assertTrue(RETURN_BOOK.isSameTask(RETURN_BOOK));

        // null -> returns false
        assertFalse(RETURN_BOOK.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedReturn = new DeadlineBuilder(RETURN_BOOK).withDescription("Return book description 2")
                .withTags("Reminder2").withDate("2023-01-01 1800").build();
        assertTrue(RETURN_BOOK.isSameTask(editedReturn));

        // different name, all other attributes same -> returns false
        editedReturn = new DeadlineBuilder(RETURN_BOOK).withName("Assignment").build();
        assertFalse(RETURN_BOOK.isSameTask(editedReturn));

        // name differs in case, all other attributes same -> returns false
        Task editedAssignment = new DeadlineBuilder(ASSIGNMENT).withName("Assignment".toLowerCase()).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "Assignment" + " ";
        editedAssignment = new DeadlineBuilder(ASSIGNMENT).withName(nameWithTrailingSpaces).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task returnCopy = new DeadlineBuilder(RETURN_BOOK).build();
        assertTrue(RETURN_BOOK.equals(returnCopy));

        // same object -> returns true
        assertTrue(RETURN_BOOK.equals(RETURN_BOOK));

        // null -> returns false
        assertFalse(RETURN_BOOK.equals(null));

        // different type -> returns false
        assertFalse(RETURN_BOOK.equals(5));

        // different task -> returns false
        assertFalse(RETURN_BOOK.equals(ASSIGNMENT));

        // different name -> returns false
        Task editedReturn = new DeadlineBuilder(RETURN_BOOK).withName("Assignment").build();
        assertFalse(RETURN_BOOK.equals(editedReturn));

        // different description -> returns false
        editedReturn = new DeadlineBuilder(RETURN_BOOK).withDescription("Assignment description").build();
        assertFalse(RETURN_BOOK.equals(editedReturn));

        // different tags -> returns false
        editedReturn = new DeadlineBuilder(RETURN_BOOK).withTags("Important").build();
        assertFalse(RETURN_BOOK.equals(editedReturn));
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

        Task zeroTagDeadline = new Deadline(new Name("zeroTag"), new Description("zeroTag"),
                zeroTag, new Date("2023-04-01 0000"), new Effort(5));
        Task oneTagDeadline = new Deadline(new Name("oneTag"), new Description("oneTag"),
                oneTag, new Date("2023-04-01 0000"), new Effort(5));
        Task twoTagDeadline = new Deadline(new Name("twoTag"), new Description("twoTag"),
                twoTag, new Date("2023-04-01 0000"), new Effort(5));

        assertEquals(1, oneTagDeadline.compareTo(zeroTagDeadline));
        assertEquals(-1, oneTagDeadline.compareTo(twoTagDeadline));
    }

    @Test
    public void compareTo_name() {
        Task aName = new Deadline(new Name("apple"), new Description("apple"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Effort(5));
        Task bName = new Deadline(new Name("bucket"), new Description("bucket"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Effort(5));
        Task cName = new Deadline(new Name("car"), new Description("car"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Effort(5));

        assertEquals(1, bName.compareTo(aName));
        assertEquals(-1, bName.compareTo(cName));
    }

    @Test
    public void compareTo_date() {
        Task morningDeadline = new Deadline(new Name("Morning"), new Description("0000 to 1200"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Effort(5));
        Task noonDeadline = new Deadline(new Name("WholeDay"), new Description("0000 to 2359"),
                new HashSet<>(), new Date("2023-04-01 1200"), new Effort(5));
        Task nightDeadline = new Deadline(new Name("Night"), new Description("1200 to 2359"),
                new HashSet<>(), new Date("2023-04-01 2359"), new Effort(5));

        assertEquals(-1, morningDeadline.compareTo(noonDeadline));
        assertEquals(-1, morningDeadline.compareTo(nightDeadline));
        assertEquals(-1, noonDeadline.compareTo(nightDeadline));
    }
}
