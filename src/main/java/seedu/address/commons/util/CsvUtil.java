package seedu.address.commons.util;

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
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * Converts a Java object instance to JSON and vice versa
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);


    /**
     * Returns the csv data from the given file or {@code Optional.empty()} object if the file is not found.
     * If any values are missing from the file, default values will be used, as long as the file is a valid csv file.
     * @param filePath cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public static Optional<String> readCsvFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("Csv file " + filePath + " not found");
            return Optional.empty();
        }

        String csvData;

        try {
            csvData = FileUtil.readFromFile(filePath);
        } catch (IOException e) {
            logger.warning("Error reading from csvFile file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }

        return Optional.of(csvData);
    }

    /**
     * Saves a csv String representation to the specified file.
     * Overwrites existing file if it exists, creates a new file if it doesn't.
     * @param csvFile cannot be null
     * @param toWrite cannot be null
     * @throws IOException if there was an error during writing to the file
     */
    public static void saveCsvFile(Path csvFile, String toWrite) throws IOException {
        requireNonNull(csvFile);
        requireNonNull(toWrite);

        FileUtil.writeToFile(csvFile, toWrite);
    }

    public static String toCsvField(String str) {
        str = str.replaceAll("\"", "\"\"");
        if (str.contains(",")) {
            str = "\"" + str + "\"";
        }
        return str;
    }

}
