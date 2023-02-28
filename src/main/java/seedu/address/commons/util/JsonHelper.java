package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.exceptions.DataConversionException;

/**
 * The singleton instance that wraps around the {@code JsonUtil} class.
 */
public class JsonHelper {
    /**
     * The singleton instance of this class.
     */
    public static final JsonHelper INSTANCE;

    static {
        INSTANCE = new JsonHelper();
    }

    private JsonHelper() {
    }

    /**
     * @see JsonUtil#readJsonFile(Path, Class)
     */
    public <T> Optional<T> readJsonFile(Path filePath, Class<T> clazz)
            throws DataConversionException {
        return JsonUtil.readJsonFile(filePath, clazz);
    }

    /**
     * @see JsonUtil#saveJsonFile(Object, Path)
     */
    public <T> void saveJsonFile(T jsonFile, Path filePath) throws IOException {
        JsonUtil.saveJsonFile(jsonFile, filePath);
    }

    /**
     * @see JsonUtil#fromJsonString(String, Class)
     */
    public <T> T fromJsonString(String jsonString, Class<T> clazz) throws IOException {
        return JsonUtil.fromJsonString(jsonString, clazz);
    }

    /**
     * @see JsonUtil#toJsonString(Object)
     */
    public <T> String toJsonString(T jsonFile) throws JsonProcessingException {
        return JsonUtil.toJsonString(jsonFile);
    }
}
