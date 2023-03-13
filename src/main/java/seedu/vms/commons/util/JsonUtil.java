package seedu.vms.commons.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Converts a Java object instance to JSON and vice versa
 */
public class JsonUtil {
    private static final String FORMAT_INVALID_JSON_FILE = "File is not in a valid JSON format: %s";

    private static ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .registerModule(new SimpleModule("SimpleModule")
                    .addSerializer(Level.class, new ToStringSerializer())
                    .addDeserializer(Level.class, new LevelDeserializer(Level.class)));


    /**
     * Deserializes a JSON file in the resource folder to an object instance.
     *
     * @param <T> - the type of the object to deserialize to.
     * @param pathString - the path to the file to deserialize in the resource
     *      folder as a String.
     * @param valueType - the type of the object to deserialize to.
     * @throws IOException if an I/O error occurs.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    public static <T> T deserializeFromResource(String pathString, Class<T> valueType)
                throws IOException {
        try (BufferedReader reader = FileUtil.getResourceFileReader(pathString)) {
            return objectMapper.readValue(reader, valueType);
        }
    }


    /**
     * Deserializes a JSON file to an object instance.
     *
     * @param <T> - the type of the object to deserialize to.
     * @param path - path to the file to deserialize from.
     * @param valueType - the type of the object to deserialize to.
     * @throws IOException if an I/O exception occurs.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    public static <T> T deserializeFromFile(Path path, Class<T> valueType)
                throws IOException {
        try (BufferedReader reader = FileUtil.getFileReader(path)) {
            return objectMapper.readValue(reader, valueType);
        } catch (JsonParseException | JsonMappingException jsonEx) {
            throw new IOException(FORMAT_INVALID_JSON_FILE, jsonEx);
        }
    }


    /**
     * Serializes the given object instance to the specified file.
     *
     * @param path - path to serialize to.
     * @param instance - the object instance to serialize.
     * @throws IOException if an I/O error occurs.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    public static void serializeToFile(Path path, Object instance) throws IOException {
        try (BufferedWriter writer = FileUtil.getFileWriter(path)) {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(writer, instance);
        }
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
            return getLoggingLevel(value);
        }

        /**
         * Gets the logging level that matches loggingLevelString
         * <p>
         * Returns null if there are no matches
         *
         */
        private Level getLoggingLevel(String loggingLevelString) {
            return Level.parse(loggingLevelString);
        }

        @Override
        public Class<Level> handledType() {
            return Level.class;
        }
    }

}
