package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.PetPal;
import seedu.address.testutil.TypicalPets;

public class JsonSerializablePetPalTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePetPalTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsPetPal.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonPetPal.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonPetPal.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializablePetPal dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializablePetPal.class).get();
        PetPal PetPalFromFile = dataFromFile.toModelType();
        PetPal typicalPersonsPetPal = TypicalPets.getTypicalPetPal();
        assertEquals(PetPalFromFile, typicalPersonsPetPal);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePetPal dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializablePetPal.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePetPal dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializablePetPal.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePetPal.MESSAGE_DUPLICATE_PET,
                dataFromFile::toModelType);
    }

}
