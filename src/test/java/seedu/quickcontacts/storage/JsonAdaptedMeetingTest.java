package seedu.quickcontacts.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.quickcontacts.storage.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.quickcontacts.testutil.TypicalMeetings.MEETING_A;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.commons.exceptions.IllegalValueException;
import seedu.quickcontacts.logic.commands.CommandTestUtil;
import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Description;
import seedu.quickcontacts.model.meeting.Location;
import seedu.quickcontacts.model.meeting.Title;
import seedu.quickcontacts.testutil.Assert;
import seedu.quickcontacts.testutil.TypicalPersons;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_TITLE = "Meeting @ CLB";
    private static final String INVALID_DATETIME = "12/13/2022 13:59"; //13 month does not exist
    private static final String INVALID_LOCATION = " City Square Mall";
    private static final String INVALID_DESCRIPTION = " Some random description";

    private static final String VALID_TITLE = MEETING_A.getTitle().toString();
    private static final String VALID_DATETIME = MEETING_A.getDateTime().getDateTime();
    private static final List<JsonAdaptedPerson> VALID_ATTENDEES = MEETING_A.getAttendees().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());
    private static final String VALID_LOCATION = MEETING_A.getLocation().toString();
    private static final String VALID_DESCRIPTION = MEETING_A.getDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalPersons.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(MEETING_A);
        assertEquals(MEETING_A, meeting.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(INVALID_TITLE, VALID_DATETIME, VALID_ATTENDEES,
                        VALID_LOCATION, VALID_DESCRIPTION);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(null, VALID_DATETIME, VALID_ATTENDEES, VALID_LOCATION, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, INVALID_DATETIME, VALID_ATTENDEES,
                        VALID_LOCATION, VALID_DESCRIPTION);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, null, VALID_ATTENDEES,
                        VALID_LOCATION, VALID_DESCRIPTION);
        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                DateTime.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidAttendees_throwsIllegalValueException() {
        List<JsonAdaptedPerson> invalidAttendees = new ArrayList<>(VALID_ATTENDEES);
        invalidAttendees.add(new JsonAdaptedPerson(CommandTestUtil.INVALID_NAME_DESC,
                CommandTestUtil.VALID_PHONE_BOB, CommandTestUtil.VALID_EMAIL_BOB,
                CommandTestUtil.VALID_ADDRESS_BOB, new ArrayList<>(VALID_TAGS)));
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, INVALID_DATETIME, invalidAttendees,
                        VALID_LOCATION, VALID_DESCRIPTION);
        Assert.assertThrows(IllegalValueException.class, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_DATETIME, VALID_ATTENDEES,
                        INVALID_LOCATION, VALID_DESCRIPTION);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_DATETIME, VALID_ATTENDEES,
                        null, VALID_DESCRIPTION);
        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                Location.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_DATETIME, VALID_ATTENDEES,
                        VALID_LOCATION, INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_DATETIME, VALID_ATTENDEES,
                        VALID_LOCATION, null);
        String expectedMessage = String.format(JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }
}
