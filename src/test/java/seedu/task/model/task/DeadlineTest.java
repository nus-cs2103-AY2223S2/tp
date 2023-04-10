package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDeadlines.ASSIGNMENT;
import static seedu.task.testutil.TypicalDeadlines.RETURN_BOOK;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void isValidDeadline() {
        // before deadline -> return true
        assertTrue(ASSIGNMENT.isValidDate(LocalDate.parse("2022-12-12")));

        // after deadline -> return false
        assertFalse(ASSIGNMENT.isValidDate(LocalDate.parse("2023-02-12")));

        // same day as deadline -> return false
        assertFalse(ASSIGNMENT.isValidDate(LocalDate.parse("2023-01-01")));
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

        // name differs in case, all other attributes same -> returns true
        Task editedAssignment = new DeadlineBuilder(ASSIGNMENT).withName("Assignment".toLowerCase()).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAssignment));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "Assignment" + " ";
        editedAssignment = new DeadlineBuilder(ASSIGNMENT).withName(nameWithTrailingSpaces).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // different effort, all other attributes same -> return true
        editedAssignment = new DeadlineBuilder(ASSIGNMENT).withEffort(3).build();
        assertTrue(ASSIGNMENT.isSameTask(editedAssignment));

        // all attributes same, different instance -> return true
        Task copyAssignment1 = new DeadlineBuilder().build();
        Task copyAssignment2 = new DeadlineBuilder().build();
        assertTrue(copyAssignment1.isSameTask(copyAssignment2));
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

        // different effort, all other attributes same -> return false
        editedReturn = new DeadlineBuilder(ASSIGNMENT).withEffort(3).build();
        assertTrue(ASSIGNMENT.isSameTask(editedReturn));

        // all attributes same, different instance -> return true
        Task copyReturn1 = new DeadlineBuilder().build();
        Task copyReturn2 = new DeadlineBuilder().build();
        assertTrue(copyReturn1.isSameTask(copyReturn2));
    }

    @Test
    public void isComingUp_success() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String validTime = now.plus(Duration.ofHours(5)).format(formatter);
        //test if the deadline is coming up
        Task deadlineComingUp = new DeadlineBuilder().withDate(validTime)
                        .withAlertWindow("6").build();
        assertTrue(deadlineComingUp.isComingUp());
    }

    @Test
    public void isComingUp_failure() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String invalidFromTime = now.minus(Duration.ofHours(1)).format(formatter);
        String farTime = now.minus(Duration.ofHours(60)).format(formatter);
        // Test for overdue deadline
        Task overdueDeadline = new DeadlineBuilder().withDate(invalidFromTime)
                .withAlertWindow("6").build();
        //deadline further than alertwindow
        Task farAwayEvent = new DeadlineBuilder().withDate(farTime)
                .withAlertWindow("6").build();

        assertFalse(overdueDeadline.isComingUp());
        assertFalse(farAwayEvent.isComingUp());
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
