package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.storage.JsonAdaptedTask.DEADLINE_EVENT_OVERLAP;
import static seedu.task.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalTasks.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.task.Date;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;
import seedu.task.model.task.exceptions.InvalidEffortException;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATE = "2 Feb 2023";
    private static final long INVALID_EFFORT = -1;

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DESCRIPTION = BENSON.getDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_DATE = "2023-01-01 1800";
    private static final long VALID_EFFORT = 1;
    private static final String EMPTY_DATE = "";

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BENSON);
        assertEquals(BENSON, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, null, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, INVALID_DATE, EMPTY_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidFromDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, INVALID_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidToDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, VALID_DATE, VALID_EFFORT);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidFromToFormat_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, VALID_DATE, VALID_EFFORT);
        JsonAdaptedTask task2 =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, VALID_DATE, EMPTY_DATE, VALID_EFFORT);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, task2::toModelType);
    }

    @Test
    public void toModelType_deadlineAndEventOverlap_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, VALID_DATE, VALID_DATE, VALID_DATE, VALID_EFFORT);
        JsonAdaptedTask task2 =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, VALID_DATE, VALID_DATE, EMPTY_DATE, VALID_EFFORT);
        JsonAdaptedTask task3 =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, VALID_DATE, EMPTY_DATE, VALID_DATE, VALID_EFFORT);
        String expectedMessage = DEADLINE_EVENT_OVERLAP;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, task2::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, task3::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, invalidTags, EMPTY_DATE, EMPTY_DATE, EMPTY_DATE, VALID_EFFORT);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidEffort_throwsInvalidEffortException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, VALID_TAGS, EMPTY_DATE, EMPTY_DATE, EMPTY_DATE, INVALID_EFFORT);
        assertThrows(InvalidEffortException.class, task::toModelType);
    }

}
