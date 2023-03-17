package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.TimeSlot;
import seedu.address.model.person.Type;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TYPE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TIMESLOT = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_TYPE = BENSON.getType().toString();
    private static final String VALID_TIMESLOT = BENSON.getTimeSlot().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();

    private static final String VALID_REMARK = "Best module ever! I love computer science!";
    private static final String VALID_DEADLINE = "20th Feb 10am";

    private static final String VALID_TEACHER = "Prof Damyth";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS, VALID_TAGS, VALID_REMARK,
                        VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS, VALID_TAGS, VALID_REMARK,
                        VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = Type.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_TIMESLOT, VALID_ADDRESS,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTimeSlot_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TYPE, INVALID_TIMESLOT, VALID_ADDRESS, VALID_TAGS,
                        VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = TimeSlot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTimeSlot_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TYPE, null, VALID_ADDRESS,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeSlot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, INVALID_ADDRESS, VALID_TAGS,
                        VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, null,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS, invalidTags,
                        VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
