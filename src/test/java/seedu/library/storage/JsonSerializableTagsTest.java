package seedu.library.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.library.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.commons.util.JsonUtil;
import seedu.library.model.Tags;
import seedu.library.testutil.TypicalTags;

public class JsonSerializableTagsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTagsTest");
    private static final Path TYPICAL_TAGS_FILE = TEST_DATA_FOLDER.resolve("typicalTags.json");
    private static final Path INVALID_TAGS_FILE = TEST_DATA_FOLDER.resolve("invalidTags.json");
    private static final Path DUPLICATE_TAGS_FILE = TEST_DATA_FOLDER.resolve("duplicateTags.json");

    @Test
    public void toModelType_typicalTagsFile_success() throws Exception {
        JsonSerializableTags dataFromFile = JsonUtil.readJsonFile(TYPICAL_TAGS_FILE,
                JsonSerializableTags.class).get();
        Tags tagsFromFile = dataFromFile.toModelType();
        Tags typicalTags = TypicalTags.getTypicalTags();
        assertEquals(tagsFromFile, typicalTags);
    }

    @Test
    public void toModelType_invalidTagsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTags dataFromFile = JsonUtil.readJsonFile(INVALID_TAGS_FILE,
                JsonSerializableTags.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTags_throwsIllegalValueException() throws Exception {
        JsonSerializableTags dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TAGS_FILE,
                JsonSerializableTags.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTags.MESSAGE_DUPLICATE_TAGS,
                dataFromFile::toModelType);
    }

}
