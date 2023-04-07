package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_1;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

class JsonAdaptedTaskTest {
    private static final String INVALID_TASK_NAME = "Complete Exercise 1.1";
    private static final String INVALID_TASK_STATUS = "PROGRESS";
    private static final String INVALID_TASK_CREATION_DATE = " ";

    private static final String VALID_TASK_NAME = VALID_TASK_1.getName().toString();
    private static final String VALID_TASK_STATUS = VALID_TASK_1.getStatus().toString();
    private static final String VALID_TASK_CREATION_DATE = VALID_TASK_1.getCreationDate().toString();

    @Test
    public void toModelType_validStudentDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK_1);
        assertEquals(VALID_TASK_1, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_TASK_NAME, VALID_TASK_STATUS, VALID_TASK_CREATION_DATE);
        String expectedMessage = Task.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_TASK_STATUS, VALID_TASK_CREATION_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK_NAME, INVALID_TASK_STATUS, VALID_TASK_CREATION_DATE);
        String expectedMessage = TaskStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK_NAME, null, VALID_TASK_CREATION_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskCreationDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_STATUS, INVALID_TASK_CREATION_DATE);
        String expectedMessage = JsonAdaptedTask.DATE_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskCreationDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_STATUS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
