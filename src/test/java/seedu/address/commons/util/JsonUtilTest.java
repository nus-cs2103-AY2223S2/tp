package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.testutil.SerializableTestClass;
import seedu.address.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");
    private static final Path NON_EXISTENT_FILE = TestUtil.getFilePathInSandboxFolder("doesNotExist.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void deserializeObjectFromJsonFile_nonExistentFile_throwsIoException() {
        assertThrows(IOException.class, () ->
                JsonUtil.deserializeObjectFromJsonFile(
                        NON_EXISTENT_FILE, SerializableTestClass.class));
    }

    @Test
    public void deserializeObjectFromJsonFile_typeMismatch_throwsIoException() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.TYPE_MISMATCH_JSON_STRING_REPRESENTATION);

        assertThrows(IOException.class, () ->
                JsonUtil.deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class));
    }

    @Test
    public void readJsonFile_validJsonFile_returnsCorrectObject() throws IOException, DataConversionException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        Optional<SerializableTestClass> serializableTestClass = JsonUtil
                .readJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.get().getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.get().getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.get().getMapOfIntegerToString(),
                SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void readJsonFile_nonExistentFile_returnsEmptyOptional() throws DataConversionException {
        assertFalse(JsonUtil.readJsonFile(
                NON_EXISTENT_FILE, SerializableTestClass.class).isPresent());
    }

    @Test
    public void readJsonFile_typeMismatch_throwsDataConversionException() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.TYPE_MISMATCH_JSON_STRING_REPRESENTATION);

        assertThrows(DataConversionException.class, () ->
                JsonUtil.readJsonFile(SERIALIZATION_FILE, SerializableTestClass.class));
    }

    @Test
    public void readJsonFile_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> JsonUtil.readJsonFile(null, SerializableTestClass.class));
    }

    @Test
    public void saveJsonFile_validJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.saveJsonFile(serializableTestClass, SERIALIZATION_FILE);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void saveJsonFile_nullJsonFile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> JsonUtil.saveJsonFile(null, SERIALIZATION_FILE));
    }

    @Test
    public void saveJsonFile_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> JsonUtil.saveJsonFile(new SerializableTestClass(), null));
    }

}
