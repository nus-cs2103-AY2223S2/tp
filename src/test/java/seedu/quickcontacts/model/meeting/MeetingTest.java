package seedu.quickcontacts.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.quickcontacts.testutil.TypicalMeetings.MEETING_A;
import static seedu.quickcontacts.testutil.TypicalMeetings.MEETING_B;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.CommandTestUtil;
import seedu.quickcontacts.testutil.Assert;
import seedu.quickcontacts.testutil.MeetingBuilder;
import seedu.quickcontacts.testutil.TypicalPersons;

public class MeetingTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Meeting meeting = new MeetingBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> meeting.getAttendees().remove(0));
    }

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(MEETING_A.isSameMeeting(MEETING_A));

        // null -> returns false
        assertFalse(MEETING_A.isSameMeeting(null));

        // same title, all other attributes different -> returns false
        Meeting editedMeetingA = new MeetingBuilder(MEETING_A).withDateTime(CommandTestUtil.VALID_MEETING_DATETIME)
                .withAttendees(TypicalPersons.BENSON).withLocation(CommandTestUtil.VALID_MEETING_LOCATION)
                .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeetingA));

        // different title, all other attributes same -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withTitle(CommandTestUtil.VALID_MEETING_TITLE).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeetingA));

        // different date/time, all other attributes same -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withDateTime(CommandTestUtil.VALID_MEETING_DATETIME).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeetingA));

        // different attendees all other attributes same -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withAttendees(TypicalPersons.BENSON).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeetingA));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting meetingACopy = new MeetingBuilder(MEETING_A).build();
        assertEquals(meetingACopy, MEETING_A);

        // same object -> returns true
        assertEquals(MEETING_A, MEETING_A);

        // null -> returns false
        assertNotEquals(null, MEETING_A);

        // different type -> returns false
        assertNotEquals(1, MEETING_A);

        // different meetings -> returns false
        assertNotEquals(MEETING_A, MEETING_B);

        // different title -> returns false
        Meeting editedMeetingA = new MeetingBuilder(MEETING_A).withTitle(CommandTestUtil.VALID_MEETING_TITLE).build();
        assertNotEquals(editedMeetingA, MEETING_A);

        // different datetime -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withDateTime(CommandTestUtil.VALID_MEETING_DATETIME).build();
        assertNotEquals(editedMeetingA, MEETING_A);

        // different attendee -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withAttendees(TypicalPersons.BENSON).build();
        assertNotEquals(editedMeetingA, MEETING_A);

        // different location -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withLocation(CommandTestUtil.VALID_MEETING_LOCATION).build();
        assertNotEquals(editedMeetingA, MEETING_A);

        // different description -> returns false
        editedMeetingA = new MeetingBuilder(MEETING_A).withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION)
                .build();
        assertNotEquals(editedMeetingA, MEETING_A);
    }

    @Test
    public void hasPassed() {
        assertTrue(new MeetingBuilder(MEETING_A).withDateTime(
                LocalDateTime.now().minus(1, ChronoUnit.HOURS)
                        .format(DateTimeFormatter.ofPattern("ddMMyyyy HH:mm"))).build().hasPassed());
        assertTrue(new MeetingBuilder(MEETING_A).withDateTime(
                LocalDateTime.now().minus(1, ChronoUnit.MINUTES)
                        .format(DateTimeFormatter.ofPattern("ddMMyyyy HH:mm"))).build().hasPassed());
        assertFalse(new MeetingBuilder(MEETING_A).withDateTime(
                LocalDateTime.now().plus(1, ChronoUnit.MINUTES)
                        .format(DateTimeFormatter.ofPattern("ddMMyyyy HH:mm"))).build().hasPassed());
        assertFalse(new MeetingBuilder(MEETING_A).withDateTime(
                LocalDateTime.now().plus(1, ChronoUnit.HOURS)
                        .format(DateTimeFormatter.ofPattern("ddMMyyyy HH:mm"))).build().hasPassed());
    }
}
