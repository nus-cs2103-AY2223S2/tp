package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalEvents.MEETING;
import static seedu.task.testutil.TypicalEvents.STUDY;

import org.junit.jupiter.api.Test;

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
}
