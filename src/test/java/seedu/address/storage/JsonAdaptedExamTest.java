package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Exam;
import seedu.address.model.student.Grade;



class JsonAdaptedExamTest {
    private class GradeStub extends Grade {
        public GradeStub() {
        }
    }
    private final Exam validExam = new Exam("valid description", LocalDateTime.now(), LocalDateTime.now(),
            0d, new GradeStub());
    private final JsonAdaptedExam validJsonExam = new JsonAdaptedExam(validExam);
    private final LocalDateTime validTime = LocalDateTime.now();
    private final String validDescription = "valid description";

    @Test
    public void constructor_validExamDetails_success() throws Exception {
        try {
            new JsonAdaptedExam(validDescription, validTime, validTime, 0d, new GradeStub());
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void constructor_validExamObject_success() throws Exception {
        try {
            new JsonAdaptedExam(validExam);
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void toModelType_validJsonAdaptedExam_success() throws Exception {
        try {
            validJsonExam.toModelType();
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void toModelType_checkReturnedExamEqualsOriginal_returnsTrue() throws Exception {
        Exam returnedValidExam = validJsonExam.toModelType();
        assertEquals(returnedValidExam, validExam);
    }
}
