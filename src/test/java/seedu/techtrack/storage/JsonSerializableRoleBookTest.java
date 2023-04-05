package seedu.techtrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.techtrack.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.techtrack.commons.exceptions.IllegalValueException;
import seedu.techtrack.commons.util.JsonUtil;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.testutil.TypicalRoles;

public class JsonSerializableRoleBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableRoleBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalRolesBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidRoleBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateRoleBook.json");

    @Test
    public void toModelType_typicalRolesFile_success() throws Exception {
        JsonSerializableRoleBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableRoleBook.class).get();
        RoleBook roleBookFromFile = dataFromFile.toModelType();
        RoleBook typicalRolesBook = TypicalRoles.getTypicalRoleBook();
        assertEquals(roleBookFromFile, typicalRolesBook);
    }

    @Test
    public void toModelType_invalidRoleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRoleBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableRoleBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateRoles_throwsIllegalValueException() throws Exception {
        JsonSerializableRoleBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableRoleBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRoleBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
