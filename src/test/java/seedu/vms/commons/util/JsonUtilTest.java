package seedu.vms.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {
    private static final Path PATH_DATA_DIR = Paths.get("src/test/data/JsonUtilTest");

    private static final Path PATH_VALID_FILE = PATH_DATA_DIR.resolve("ValidFile.json");
    private static final Path PATH_INVALID_JSON_FORMAT = PATH_DATA_DIR.resolve("InvalidJsonFormat.json");
    private static final Path PATH_INVALID_JSON_MAPPING = PATH_DATA_DIR.resolve("InvalidJsonMapping.json");
    private static final Path PATH_NON_EXISTENT_FILE = PATH_DATA_DIR.resolve("PathThatDoesNotExist.json");


    @Test
    public void deserializeFromFile_validFile_objectCreated() throws Exception {
        StubObject deserialized = deserializeFromFile(PATH_VALID_FILE);
        assertEquals(new StubObject(), deserialized);
    }


    @Test
    public void deserializeFromFile_invalidJsonFormat_exceptionThrown() {
        assertThrows(IOException.class, () -> deserializeFromFile(PATH_INVALID_JSON_FORMAT));
        assertThrows(IOException.class, () -> deserializeFromFile(PATH_INVALID_JSON_MAPPING));
    }


    @Test
    public void deserializeFromFile_nonExistentFile_exceptionThrown() {
        assertThrows(IOException.class, () -> deserializeFromFile(PATH_NON_EXISTENT_FILE));
    }


    private StubObject deserializeFromFile(Path path) throws Exception {
        return JsonUtil.deserializeFromFile(path, StubObject.class);
    }





    private static class StubObject {
        private static final int DEFAULT_INT_PROPERTY = 5;
        private static final String DEFAULT_STRING_PROPERTY = "five";

        private final int intProperty;
        private final String stringProperty;


        @JsonCreator
        public StubObject(
                    @JsonProperty("intProperty") int intProperty,
                    @JsonProperty("stringProperty") String stringProperty) {
            this.intProperty = intProperty;
            this.stringProperty = stringProperty;
        }


        public StubObject() {
            this(DEFAULT_INT_PROPERTY, DEFAULT_STRING_PROPERTY);
        }


        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (!(other instanceof StubObject)) {
                return false;
            }

            StubObject casted = (StubObject) other;
            return intProperty == casted.intProperty
                    && stringProperty.equals(casted.stringProperty);
        }
    }
}
