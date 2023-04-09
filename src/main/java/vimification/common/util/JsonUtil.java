package vimification.common.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Converts a Java object instance to JSON and vice versa
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .findAndRegisterModules()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .registerModule(new SimpleModule("SimpleModule")
                    .addSerializer(Level.class, new ToStringSerializer())
                    .addDeserializer(Level.class, new LevelDeserializer(Level.class)));

    /**
     * Converts a given instance of a class into its JSON data string representation.
     * @param <T> The generic type to create an instance of
     * @return The JSON string representation of the instance
     * @throws JsonProcessingException if the object cannot be converted to JSON
     */
    static <T> void serializeObjectToJsonFile(Path filePath, T object) throws IOException {
        FileUtil.createIfMissing(filePath);
        FileUtil.writeToFile(filePath, toJsonString(object));
    }

    /**
     * Converts a given instance of a class into its JSON data string representation.
     * @param <T> The generic type to create an instance of
     * @return The JSON string representation of the instance
     * @throws JsonProcessingException if the object cannot be converted to JSON
     */
    static <T> T deserializeObjectFromJsonFile(Path filePath, Class<T> classOfObject)
            throws IOException {
        return fromJsonString(FileUtil.readFromFile(filePath), classOfObject);
    }

    /**
     * Returns the Json object from the given file or {@code Optional.empty()} object if the file is
     * not found. If any values are missing from the file, default values will be used, as long as
     * the file is a valid json file.
     *
     * @param filePath cannot be null
     * @param classOfObject Json file has to correspond to the structure in the class given here
     * @throws IOException if the file cannot be read
     */
    public static <T> T readJsonFile(Path filePath, Class<T> classOfObject) throws IOException {
        requireNonNull(filePath);
        return deserializeObjectFromJsonFile(filePath, classOfObject);
    }

    /**
     * Saves the Json object to the specified file. Overwrites existing file if it exists, creates a
     * new file if it doesn't.
     *
     * @param object cannot be null
     * @param filePath cannot be null
     * @throws IOException if there was an error during writing to the file
     */
    public static <T> void saveJsonFile(T object, Path filePath) throws IOException {
        requireNonNull(object);
        requireNonNull(filePath);
        serializeObjectToJsonFile(filePath, object);
    }


    /**
     * Converts a given string representation of a JSON data to instance of a class
     *
     * @param <T> The generic type to create an instance of
     * @return The instance of T with the specified values in the JSON string
     */
    public static <T> T fromJsonString(String json, Class<T> instanceClass) throws IOException {
        return OBJECT_MAPPER.readValue(json, instanceClass);
    }

    /**
     * Converts a given instance of a class into its JSON data string representation.
     *
     * @param <T> The generic type to create an instance of
     * @param instance The T object to be converted into the JSON string
     * @return JSON data representation of the given class instance, in string
     */
    public static <T> String toJsonString(T instance) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
    }

    /**
     * Contains methods that retrieve logging level from serialized string.
     */
    private static class LevelDeserializer extends FromStringDeserializer<Level> {

        protected LevelDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        protected Level _deserialize(String value, DeserializationContext ctxt) {
            return Level.parse(value);
        }

        @Override
        public Class<Level> handledType() {
            return Level.class;
        }
    }

}
