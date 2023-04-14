package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T_LEC;
import static seedu.address.testutil.TypicalModules.CS3230;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Address;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Name;
import seedu.address.model.module.Resource;
import seedu.address.model.module.TimeSlot;

public class JsonAdaptedModuleTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TYPE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TIMESLOT = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DEADLINE = "23rd Feb 2022";



    private static final String VALID_NAME = CS3230.getName().toString();
    private static final String VALID_TYPE = "Lecture";
    private static final String VALID_TIMESLOT = "Tuesday 12:00 15:00";
    private static final String VALID_ADDRESS = CS3230.getAddress().toString();
    private static final String VALID_REMARK = "Best module ever! I love computer science!";
    private static final String VALID_DEADLINE = "120623 15:00";

    private static final String VALID_TEACHER = "Prof Damyth";
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2103T_LEC.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2103T_LEC);
        assertEquals(CS2103T_LEC, module.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_NAME, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS, VALID_TAGS, VALID_REMARK,
                        VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS, VALID_TAGS, VALID_REMARK,
                        INVALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS_INVALID_DATE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, null, VALID_TIMESLOT, VALID_ADDRESS,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Resource.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTimeSlot_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_TYPE, INVALID_TIMESLOT, VALID_ADDRESS, VALID_TAGS,
                        VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = TimeSlot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullTimeSlot_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, VALID_TYPE, null, VALID_ADDRESS,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeSlot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, INVALID_ADDRESS, VALID_TAGS,
                        VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, null,
                VALID_TAGS, VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_TYPE, VALID_TIMESLOT, VALID_ADDRESS, invalidTags,
                        VALID_REMARK, VALID_DEADLINE, VALID_TEACHER);
        assertThrows(IllegalValueException.class, module::toModelType);
    }

}
