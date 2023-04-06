package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internship.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalEvents.EM11;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;
import seedu.internship.model.internship.Internship;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = ""; // Anything can be an Event Name, but it cannot be empty
    private static final String INVALID_START = "2002";
    private static final String INVALID_END = "32/04/2023 2500";

    private static final String VALID_NAME = EM11.getName().name;
    private static final String VALID_START = EM11.getStart().getNumericDateTimeString();
    private static final String VALID_END = EM11.getEnd().getNumericDateTimeString();
    private static final String VALID_DESCRIPTION = EM11.getDescription().descriptionMessage;
    private static final Internship VALID_INTERNSHIP = EM11.getInternship();

    @Test
    public void toModelType_validEventDetails_returnEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(EM11);
        assertFalse(EM11.isClash(event.toModelType()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_START, VALID_END, VALID_DESCRIPTION,
                        new JsonAdaptedInternship(VALID_INTERNSHIP));
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_START, VALID_END, VALID_DESCRIPTION,
                        new JsonAdaptedInternship(VALID_INTERNSHIP));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidStart_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_START, VALID_END, VALID_DESCRIPTION,
                        new JsonAdaptedInternship(VALID_INTERNSHIP));
        String expectedMessage = Start.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullStart_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, null, VALID_END, VALID_DESCRIPTION,
                        new JsonAdaptedInternship(VALID_INTERNSHIP));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Start.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEnd_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START, INVALID_END, VALID_DESCRIPTION,
                        new JsonAdaptedInternship(VALID_INTERNSHIP));
        String expectedMessage = End.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEnd_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START, null, VALID_DESCRIPTION,
                        new JsonAdaptedInternship(VALID_INTERNSHIP));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, End.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

}
