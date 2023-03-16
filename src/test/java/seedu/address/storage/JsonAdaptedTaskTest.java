package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shared.Id;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

class JsonAdaptedTaskTest {


    private static final String VALID_ID = UUID.randomUUID().toString();
    private static final String VALID_SUBJECT = "Math homework";
    private static final String VALID_CONTENT = "Do exercises 1 to 10 in Chapter 3";
    private static final String VALID_STATUS = "true";
    private static final String INVALID_ID = "a";
    private static final String INVALID_SUBJECT = "Math homework #1";
    private static final String INVALID_CONTENT = "";
    private static final String INVALID_STATUS = "yes";

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, VALID_SUBJECT, VALID_CONTENT, VALID_STATUS);
        Task expectedTask = new Task(new Title(VALID_SUBJECT), new Content(VALID_CONTENT),
            new Status(true), new Id(VALID_ID));
        assertEquals(expectedTask, jsonTask.toModelType());
    }

    @Test
    public void toModelType_missingId_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(null, VALID_SUBJECT, VALID_CONTENT, VALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(INVALID_ID, VALID_SUBJECT, VALID_CONTENT, VALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_missingSubject_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, null, VALID_CONTENT, VALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, INVALID_SUBJECT, VALID_CONTENT, VALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_missingContent_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, VALID_SUBJECT, null, VALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_invalidContent_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, VALID_SUBJECT, INVALID_CONTENT, VALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_missingStatus_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, VALID_SUBJECT, VALID_CONTENT, null);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask jsonTask = new JsonAdaptedTask(VALID_ID, VALID_SUBJECT, VALID_CONTENT, INVALID_STATUS);
        assertThrows(IllegalValueException.class, jsonTask::toModelType);
    }

}
