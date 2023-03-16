package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.shared.Id;

class JsonAdaptedAssignTaskTest {
    private static final String VALID_PERSON_ID = UUID.randomUUID().toString();
    private static final String VALID_TASK_ID = UUID.randomUUID().toString();
    private static final String INVALID_ID = "a";

    @Test
    public void toModelType_validPersonTaskDetails_returnsPersonTask() throws Exception {
        JsonAdaptedAssignTask jsonPersonTask = new JsonAdaptedAssignTask(VALID_PERSON_ID, VALID_TASK_ID);
        AssignTask expectedAssignTask = new AssignTask(new Id(VALID_PERSON_ID), new Id(VALID_TASK_ID));
        assertEquals(expectedAssignTask, jsonPersonTask.toModelType());
    }

    @Test
    public void toModelType_invalidPersonId_throwsIllegalValueException() {
        JsonAdaptedAssignTask jsonPersonTask = new JsonAdaptedAssignTask(INVALID_ID, VALID_TASK_ID);
        assertThrows(IllegalValueException.class, jsonPersonTask::toModelType);
    }

    @Test
    public void toModelType_invalidTaskId_throwsIllegalValueException() {
        JsonAdaptedAssignTask jsonPersonTask = new JsonAdaptedAssignTask(VALID_PERSON_ID, INVALID_ID);
        assertThrows(IllegalValueException.class, jsonPersonTask::toModelType);
    }
}
