package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.model.student.Exam;
import seedu.address.model.student.Lesson;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedLessonTest {
    private final String validTitle = "valid title";
    private final LocalDateTime validDateTime = LocalDateTime.now();
    private final Lesson validLesson = new Lesson(validTitle, validDateTime, validDateTime);
    private final JsonAdaptedLesson validJsonLesson = new JsonAdaptedLesson(validLesson);

    @Test
    public void constructor_validLessonDetails_success() throws Exception {
        try {
            new JsonAdaptedLesson(validTitle, validDateTime, validDateTime);
            new JsonAdaptedLesson(validLesson);
        } catch (Exception e) {
            assert(false);
        }
    }
    @Test
    public void toModelType_validJsonAdaptedLesson_success() throws Exception {
        try {
            validJsonLesson.toModelType();
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void toModelType_CheckReturnedLessonEqualsOriginal_returnsTrue() throws Exception {
        Lesson returnedValidLesson = validJsonLesson.toModelType();
        assertEquals(returnedValidLesson, validLesson);
    }

    @Test
    public void toModelType_CheckReturnedLessonFields_success() throws Exception {
        JsonAdaptedLesson adaptedHomework = new JsonAdaptedLesson(validLesson);
        assertEquals(adaptedHomework.getTitle(), validLesson.getTitle());
        assertEquals(adaptedHomework.getStartTime(), validLesson.getStartTime());
        assertEquals(adaptedHomework.getEndTime(), validLesson.getEndTime());
    }
}
