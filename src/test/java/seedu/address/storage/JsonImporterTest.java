package seedu.address.storage;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.storage.exceptions.JsonNotFoundException;

class JsonImporterTest {

    private static final Path TEST_DATA_FOLDER = Path.of("src", "test", "data", "JsonImporterTest");

    @Test
    void readData_validData_success() {
        Path validData = TEST_DATA_FOLDER.resolve("validImport.json");
        JsonImporter successImporter = new JsonImporter(validData);

        try {
            successImporter.readData();
        } catch (DataConversionException | JsonNotFoundException e) {
            throw new AssertionError("readData should run successfully");
        }
    }

    @Test
    void readData_validDataWithTagsGroups_success() {
        Path validDataTagGroup = TEST_DATA_FOLDER.resolve("validImportWithTagsGroups.json");
        JsonImporter successImporterTagGroup = new JsonImporter(validDataTagGroup);

        try {
            successImporterTagGroup.readData();
        } catch (DataConversionException | JsonNotFoundException e) {
            throw new AssertionError("readData should run successfully");
        }
    }

    @Test
    void readData_invalidFilePath_throwsEmptyAddressBookException() {
        Path invalidPath = TEST_DATA_FOLDER.resolve("fileDoesNotExist.json");
        JsonImporter invalidImporter = new JsonImporter(invalidPath);

        try {
            invalidImporter.readData();
        } catch (DataConversionException e) {
            throw new AssertionError("readData should not throw DataConversionException.");
        } catch (JsonNotFoundException e) {
            return;
        }
        throw new AssertionError("readData did not throw an Exception");
    }

    @Test
    void readData_invalidDataEmpty_throwsDataConversionException() {
        Path emptyData = TEST_DATA_FOLDER.resolve("invalidImportEmpty.json");
        JsonImporter emptyImporter = new JsonImporter(emptyData);

        try {
            emptyImporter.readData();
        } catch (DataConversionException e) {
            return;
        } catch (JsonNotFoundException e) {
            throw new AssertionError("readData should not throw DataConversionException");
        }
        throw new AssertionError("readData should not run successfully");
    }

    @Test
    void readData_invalidDataIncorrectFormat_throwsDataConversionException() {
        Path invalidData = TEST_DATA_FOLDER.resolve("invalidImportIncorrectFormat.json");
        JsonImporter invalidImporter = new JsonImporter(invalidData);

        try {
            invalidImporter.readData();
        } catch (DataConversionException e) {
            return;
        } catch (JsonNotFoundException e) {
            throw new AssertionError("readData should not throw JsonNotFoundException");
        }
        throw new AssertionError("readData should not run successfully");
    }
}
