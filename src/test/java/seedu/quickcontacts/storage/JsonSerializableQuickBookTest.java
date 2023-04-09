package seedu.quickcontacts.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.quickcontacts.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.commons.exceptions.IllegalValueException;
import seedu.quickcontacts.commons.util.JsonUtil;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.testutil.TypicalQuickBooks;

public class JsonSerializableQuickBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableQuickBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsQuickBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonQuickBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonQuickBook.json");
    private static final Path TYPICAL_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("typicalMeetingsQuickBook.json");
    private static final Path INVALID_MEETING_FILE = TEST_DATA_FOLDER.resolve("invalidMeetingQuickBook.json");
    private static final Path DUPLICATE_MEETING_FILE = TEST_DATA_FOLDER.resolve("duplicateMeetingQuickBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableQuickBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableQuickBook.class).get();
        QuickBook quickBookFromFile = dataFromFile.toModelType();
        QuickBook typicalPersonsQuickBook = TypicalQuickBooks.getTypicalQuickBookPersonsOnly();
        assertEquals(quickBookFromFile, typicalPersonsQuickBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableQuickBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableQuickBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableQuickBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableQuickBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableQuickBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalMeetingsFile_success() throws Exception {
        JsonSerializableQuickBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEETINGS_FILE,
                JsonSerializableQuickBook.class).get();
        QuickBook quickBookFromFile = dataFromFile.toModelType();
        QuickBook typicalMeetingsQuickBook = TypicalQuickBooks.getTypicalQuickBookMeetingsOnly();
        assertEquals(quickBookFromFile, typicalMeetingsQuickBook);
    }

    @Test
    public void toModelType_invalidMeetingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableQuickBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEETING_FILE,
                JsonSerializableQuickBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMeetings_throwsIllegalValueException() throws Exception {
        JsonSerializableQuickBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEETING_FILE,
                JsonSerializableQuickBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableQuickBook.MESSAGE_DUPLICATE_MEETING,
                dataFromFile::toModelType);
    }
}
