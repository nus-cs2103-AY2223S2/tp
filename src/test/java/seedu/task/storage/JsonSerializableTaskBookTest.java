package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.JsonUtil;
import seedu.task.model.TaskBook;
import seedu.task.testutil.TypicalTasks;

//@@author
public class JsonSerializableTaskBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskBookTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskBook.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskBook.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTaskBook.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTaskBook.class).get();
        TaskBook taskBookFromFile = dataFromFile.toModelType();
        TaskBook typicalTasksTaskBook = TypicalTasks.getTypicalTaskBook();
        assertEquals(taskBookFromFile, typicalTasksTaskBook);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTaskBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskBook.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
