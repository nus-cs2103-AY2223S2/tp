package seedu.wife.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.wife.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

//import org.junit.jupiter.api.Test;

//import seedu.wife.commons.exceptions.IllegalValueException;
//import seedu.wife.commons.util.JsonUtil;
//import seedu.wife.model.Wife;
//import seedu.wife.testutil.TypicalFood;

public class JsonSerializableWifeTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    /*
    @Test
    public void toModelType_typicalFoodFile_success() throws Exception {
        JsonSerializableWife dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableWife.class).get();
        Wife wifeFromFile = dataFromFile.toModelType();
        Wife typicalWife = TypicalFood.getTypicalWife();
        assertEquals(wifeFromFile, typicalWife);
    }
    */

    /*
    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWife dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableWife.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
    */

    /*
    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableWife dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableWife.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWife.MESSAGE_DUPLICATE_FOODS,
                dataFromFile::toModelType);
    }
    */
}
