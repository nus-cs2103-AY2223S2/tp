package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Homework;
import seedu.address.model.student.Homework.Status;
import seedu.address.testutil.HomeworkBuilder;

public class JsonAdaptedHomeworkTest {

    private static final Homework HOMEWORK_ONE = new HomeworkBuilder()
            .withDescription("Complete maths homework")
            .withDeadline(LocalDateTime.of(2022, 3, 5, 23, 59))
            .build();
    private static final Homework HOMEWORK_TWO = new HomeworkBuilder()
            .withDescription("Submit science report")
            .withDeadline(LocalDateTime.of(2022, 4, 10, 16, 0))
            .withStatus(Status.COMPLETED)
            .build();

    @Test
    public void constructor_validHomeworkDetails_success() throws Exception {
        JsonAdaptedHomework adaptedHomework = new JsonAdaptedHomework(HOMEWORK_ONE);
        assertEquals(HOMEWORK_ONE.getDescription(), adaptedHomework.getDescription());
        assertEquals(HOMEWORK_ONE.getDeadline(), adaptedHomework.getDeadline());
        assertEquals(HOMEWORK_ONE.getStatus(), adaptedHomework.getStatus());
    }

    @Test
    public void toModelType_validHomeworkDetails_success() {
        JsonAdaptedHomework adaptedHomework = new JsonAdaptedHomework(HOMEWORK_ONE);
        assertEquals(HOMEWORK_ONE, adaptedHomework.toModelType());
    }

    @Test
    public void toModelType_completedStatus_success() {
        JsonAdaptedHomework adaptedHomework = new JsonAdaptedHomework(HOMEWORK_TWO);
        assertEquals(HOMEWORK_TWO, adaptedHomework.toModelType());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        JsonAdaptedHomework adaptedHomework = new JsonAdaptedHomework(HOMEWORK_ONE);
        assertEquals(adaptedHomework, adaptedHomework);
    }

    @Test
    public void equals_equalObjects_returnsTrue() {
        JsonAdaptedHomework adaptedHomeworkOne = new JsonAdaptedHomework(HOMEWORK_ONE);
        JsonAdaptedHomework adaptedHomeworkTwo = new JsonAdaptedHomework(HOMEWORK_ONE);
        assertEquals(adaptedHomeworkOne, adaptedHomeworkTwo);
    }

    @Test
    public void equals_differentObjects_returnsFalse() {
        JsonAdaptedHomework adaptedHomeworkOne = new JsonAdaptedHomework(HOMEWORK_ONE);
        JsonAdaptedHomework adaptedHomeworkTwo = new JsonAdaptedHomework(HOMEWORK_TWO);
        assertNotEquals(adaptedHomeworkOne, adaptedHomeworkTwo);
    }

    @Test
    public void hashCode_equalObjects_returnsTrue() {
        JsonAdaptedHomework adaptedHomeworkOne = new JsonAdaptedHomework(HOMEWORK_ONE);
        JsonAdaptedHomework adaptedHomeworkTwo = new JsonAdaptedHomework(HOMEWORK_ONE);
        assertEquals(adaptedHomeworkOne.hashCode(), adaptedHomeworkTwo.hashCode());
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        JsonAdaptedHomework adaptedHomework =
                new JsonAdaptedHomework(null, HOMEWORK_ONE.getDeadline(), HOMEWORK_ONE.getStatus());
        assertThrows(NullPointerException.class, adaptedHomework::toModelType);
    }

    @Test
    public void constructor_nullDeadline_throwsNullPointerException() {
        JsonAdaptedHomework adaptedHomework =
                new JsonAdaptedHomework(HOMEWORK_ONE.getDescription(), null, HOMEWORK_ONE.getStatus());
        assertThrows(NullPointerException.class, adaptedHomework::toModelType);
    }
}
