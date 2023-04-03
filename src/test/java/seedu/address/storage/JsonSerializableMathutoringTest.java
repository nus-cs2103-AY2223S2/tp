package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Mathutoring;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableMathutoringTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMathutoringTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsMathutoring.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentMathutoring.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentMathutoring.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableMathutoring dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableMathutoring.class).get();
        Mathutoring mathutoringFromFile = dataFromFile.toModelType();
        Mathutoring typicalStudentsMathutoring = TypicalStudents.getTypicalMathutoring();
        assertEquals(mathutoringFromFile, typicalStudentsMathutoring);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMathutoring dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableMathutoring.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableMathutoring dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableMathutoring.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMathutoring.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
