package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalEvents.MEETING;
import static seedu.task.testutil.TypicalEvents.STUDY;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.task.model.tag.Tag;
import seedu.task.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameDeadline() {
        // same object -> returns true
        assertTrue(MEETING.isSameTask(MEETING));

        // null -> returns false
        assertFalse(MEETING.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedMeeting = new EventBuilder(MEETING).withDescription("Meeting description 2")
                .withTags("Reminder2").withFrom("2023-01-01 1900").withTo("2023-01-02 1900").build();
        assertTrue(MEETING.isSameTask(editedMeeting));

        // different name, all other attributes same -> returns false
        editedMeeting = new EventBuilder(MEETING).withName("Assignment").build();
        assertFalse(MEETING.isSameTask(editedMeeting));

        // name differs in case, all other attributes same -> returns false
        Task editedStudy = new EventBuilder(STUDY).withName("Study".toLowerCase()).build();
        assertFalse(STUDY.isSameTask(editedStudy));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "Study" + " ";
        editedStudy = new EventBuilder(STUDY).withName(nameWithTrailingSpaces).build();
        assertFalse(STUDY.isSameTask(editedStudy));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task meetingCopy = new EventBuilder(MEETING).build();
        assertTrue(MEETING.equals(meetingCopy));

        // same object -> returns true
        assertTrue(MEETING.equals(MEETING));

        // null -> returns false
        assertFalse(MEETING.equals(null));

        // different type -> returns false
        assertFalse(MEETING.equals(5));

        // different task -> returns false
        assertFalse(MEETING.equals(STUDY));

        // different name -> returns false
        Task editedMeeting = new EventBuilder(MEETING).withName("Study").build();
        assertFalse(MEETING.equals(editedMeeting));

        // different description -> returns false
        editedMeeting = new EventBuilder(MEETING).withDescription("Study description").build();
        assertFalse(MEETING.equals(editedMeeting));

        // different tags -> returns false
        editedMeeting = new EventBuilder(MEETING).withTags("Important").build();
        assertFalse(MEETING.equals(editedMeeting));
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

        Task zeroTagEvent = new Event(new Name("zeroTag"), new Description("zeroTag"),
                zeroTag, new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(5));
        Task oneTagEvent = new Event(new Name("oneTag"), new Description("oneTag"),
                oneTag, new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(3));
        Task twoTagEvent = new Event(new Name("twoTag"), new Description("twoTag"),
                twoTag, new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(2));

        assertEquals(1, oneTagEvent.compareTo(zeroTagEvent));
        assertEquals(-1, oneTagEvent.compareTo(twoTagEvent));
    }

    @Test
    public void compareTo_name() {
        Task aName = new Event(new Name("apple"), new Description("apple"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(5));
        Task bName = new Event(new Name("bucket"), new Description("car"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(2));
        Task cName = new Event(new Name("car"), new Description("car"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Date("2023-04-01 0100"), new Effort(1));

        assertEquals(1, bName.compareTo(aName));
        assertEquals(-1, bName.compareTo(cName));
    }

    @Test
    public void compareTo_date() {
        Task morningEvent = new Event(new Name("Morning"), new Description("0000 to 1200"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Date("2023-04-01 1200"), new Effort(5));
        Task wholeDayEvent = new Event(new Name("WholeDay"), new Description("0000 to 2359"),
                new HashSet<>(), new Date("2023-04-01 0000"), new Date("2023-04-01 2359"), new Effort(5));
        Task nightEvent = new Event(new Name("Night"), new Description("1200 to 2359"),
                new HashSet<>(), new Date("2023-04-01 1200"), new Date("2023-04-01 2359"), new Effort(5));

        assertEquals(-1, morningEvent.compareTo(wholeDayEvent));
        assertEquals(-1, morningEvent.compareTo(nightEvent));
        assertEquals(-1, wholeDayEvent.compareTo(nightEvent));
    }
}
