package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.shared.Id;

class JsonAdaptedPersonTaskTest {
    private static final String VALID_PERSON_ID = UUID.randomUUID().toString();
    private static final String VALID_TASK_ID = UUID.randomUUID().toString();
    private static final String INVALID_ID = "a";

    @Test
    public void toModelType_validPersonTaskDetails_returnsPersonTask() throws Exception {
        JsonAdaptedPersonTask jsonPersonTask = new JsonAdaptedPersonTask(VALID_PERSON_ID, VALID_TASK_ID);
        PersonTask expectedPersonTask = new PersonTask(new Id(VALID_PERSON_ID), new Id(VALID_TASK_ID));
        assertEquals(expectedPersonTask, jsonPersonTask.toModelType());
    }

    @Test
    public void toModelType_invalidPersonId_throwsIllegalValueException() {
        JsonAdaptedPersonTask jsonPersonTask = new JsonAdaptedPersonTask(INVALID_ID, VALID_TASK_ID);
        assertThrows(IllegalValueException.class, jsonPersonTask::toModelType);
    }

    @Test
    public void toModelType_invalidTaskId_throwsIllegalValueException() {
        JsonAdaptedPersonTask jsonPersonTask = new JsonAdaptedPersonTask(VALID_PERSON_ID, INVALID_ID);
        assertThrows(IllegalValueException.class, jsonPersonTask::toModelType);
    }
}
