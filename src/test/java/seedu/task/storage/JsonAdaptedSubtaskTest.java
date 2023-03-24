package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.storage.JsonAdaptedSubtask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalSubtasks.ALICE_HOMEWORK;

import org.junit.jupiter.api.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;

public class JsonAdaptedSubtaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DESCRIPTION = "";
    private static final String VALID_NAME = ALICE_HOMEWORK.getName().toString();

    private static final String VALID_DESCRIPTION = ALICE_HOMEWORK.getDescription().toString();



    @Test
    public void toModelType_validTaskDetails_returnsSubtask() throws Exception {
        JsonAdaptedSubtask task = new JsonAdaptedSubtask(ALICE_HOMEWORK);
        assertEquals(ALICE_HOMEWORK, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSubtask task =
            new JsonAdaptedSubtask(INVALID_NAME, VALID_DESCRIPTION);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSubtask task =

            new JsonAdaptedSubtask(null, VALID_DESCRIPTION);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedSubtask task =

            new JsonAdaptedSubtask(VALID_NAME, INVALID_DESCRIPTION);

        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedSubtask task =

            new JsonAdaptedSubtask(VALID_NAME, null);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }



}
