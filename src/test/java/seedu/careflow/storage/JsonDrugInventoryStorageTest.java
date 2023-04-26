package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalDrugs.ACYCLOVIR;
import static seedu.careflow.testutil.TypicalDrugs.IBUPROFEN;
import static seedu.careflow.testutil.TypicalDrugs.IMODIUM;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;

class JsonDrugInventoryStorageTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonDrugInventoryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDrugInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDrugInventory(null));
    }

    private Optional<ReadOnlyDrugInventory> readDrugInventory(String filePath) throws Exception {
        return new JsonDrugInventoryStorage(
                Paths.get(filePath)).readDrugInventory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDrugInventory("abc.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() throws Exception {
        assertThrows(DataConversionException.class, () ->
                readDrugInventory("notJsonFormatDrugInventory.json"));
    }

    @Test
    public void read_invalidDrugDrugInventory_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readDrugInventory("invalidDrugDrugInventory.json"));
    }

    @Test
    public void read_invalidAndValidDrugDrugInventory_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readDrugInventory("invalidAndValidDrugDrugInventory.json"));
    }

    @Test
    public void read_extraFieldDrugDrugInventory_extraIgnored() throws Exception {
        Optional<ReadOnlyDrugInventory> actual = readDrugInventory("extraFieldDrugDrugInventory.json");
        Optional<ReadOnlyDrugInventory> expected = readDrugInventory("validDrugDrugInventory.json");
        assertEquals(actual, expected);
    }

    @Test
    public void read_missingFieldDrugDrugInventory_throwDataConversionException() throws Exception {
        assertThrows(DataConversionException.class, ()
                -> readDrugInventory("missingFieldDrugDrugInventory.json"));
    }

    @Test
    public void read_nullFieldDrugDrugInventory_throwDataConversionException() throws Exception {
        assertThrows(DataConversionException.class, ()
                -> readDrugInventory("nullFieldDrugDrugInventory.json"));
    }

    @Test
    public void readAndSaveDrugInventory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDrugInventory.json");
        DrugInventory original = getTypicalDrugInventory();
        JsonDrugInventoryStorage jsonDrugInventoryStorage = new JsonDrugInventoryStorage(filePath);

        // save in and read back
        jsonDrugInventoryStorage.saveDrugInventory(original, filePath);
        ReadOnlyDrugInventory readBack = jsonDrugInventoryStorage.readDrugInventory(filePath).get();
        assertEquals(original, new DrugInventory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addDrug(ACYCLOVIR);
        original.removeDrug(IMODIUM);
        jsonDrugInventoryStorage.saveDrugInventory(original, filePath);
        readBack = jsonDrugInventoryStorage.readDrugInventory(filePath).get();
        assertEquals(original, new DrugInventory(readBack));

        // save and read without specifying file path
        original.addDrug(IBUPROFEN);
        jsonDrugInventoryStorage.saveDrugInventory(original);
        readBack = jsonDrugInventoryStorage.readDrugInventory().get();
        assertEquals(original, new DrugInventory(readBack));
    }

    @Test
    public void saveDrugInventory_nullDrugInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> saveDrugInventory(null, "someFile.json"));
    }

    private void saveDrugInventory(ReadOnlyDrugInventory drugInventory, String filePath) throws Exception {
        try {
            new JsonDrugInventoryStorage(Paths.get(filePath)).saveDrugInventory(drugInventory,
                    addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }

    @Test
    public void saveDrugInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> saveDrugInventory(new DrugInventory(), null));
    }
}
