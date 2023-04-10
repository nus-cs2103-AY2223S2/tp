package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "T@sk";
    private static final String INVALID_DEADLINE = "99/99/9999";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_TIME_ADDED = "99/99/9999";

    private static final String VALID_NAME = SORT_INVENTORY_N.getTaskName().toString();
    private static final String VALID_DEADLINE = SORT_INVENTORY_N.getTaskDeadline().toJsonString();
    private static final String VALID_STATUS = SORT_INVENTORY_N.getTaskStatus().toJsonString();
    private static final String VALID_TIME_ADDED = LocalDateTime.now().toString();

    //@@author liumc-sg-reused
    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(SORT_INVENTORY_N);
        assertEquals(SORT_INVENTORY_N, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_DEADLINE, VALID_STATUS, VALID_TIME_ADDED);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE, VALID_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_DEADLINE, VALID_STATUS, VALID_TIME_ADDED);
        String expectedMessage = TaskDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_STATUS, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskStatus_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, INVALID_STATUS, VALID_TIME_ADDED);
        String expectedMessage = TaskStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, null, VALID_TIME_ADDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    //@@author

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void toModelType_invalidTimeAdded_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_STATUS, INVALID_TIME_ADDED);
        String expectedMessage = JsonAdaptedTask.MESSAGE_PARSE_TIME_ADDED_ERROR;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTimeAdded_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_STATUS, null);
        String expectedMessage = JsonAdaptedTask.MESSAGE_PARSE_TIME_ADDED_ERROR;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    //@@author
}
