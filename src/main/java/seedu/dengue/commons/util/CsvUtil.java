package seedu.dengue.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.exceptions.DataConversionException;

/**
 * Converts a Java object instance to CSV and vice versa
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);

    /**
     * Returns the Csv object from the given file or {@code Optional.empty()} object if the file is not found.
     * If any values are missing from the file, default values will be used, as long as the file is a valid csv file.
     * @param filePath cannot be null.
     * @param classOfObjectToDeserialize Csv file has to correspond to the structure in the class given here.
     * @throws DataConversionException if the file format is not as expected.
     */
    public static <T> Optional<List<T>> readCsvFile(
            Path filePath, Class<T> classOfObjectToDeserialize) throws DataConversionException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("Csv file " + filePath + " not found");
            return Optional.empty();
        }

        List<T> csvFile;

        try {
            csvFile = readFromCsvFile(filePath, classOfObjectToDeserialize);
        } catch (IOException e) {
            logger.warning("Error reading from Csv file " + filePath + ": " + e);
            throw new DataConversionException(e);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            throw new DataConversionException(e);
        }

        return Optional.of(csvFile);
    }

    /**
     * Saves the Csv object to the specified file.
     * Overwrites existing file if it exists, creates a new file if it doesn't.
     * @param data cannot be null
     * @param filePath cannot be null
     * @parem mappingStrategy
     * @throws IOException if there was an error during writing to the file
     */
    public static <T> void saveCsvFile(List<String[]> data, Path filePath, String[] header)
            throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        requireNonNull(filePath);
        requireNonNull(data);

        writeToCsvFile(filePath, header, data);
    }

    public static <T> CsvToBean<T> createCsvReader(Reader r, Class<T> instanceClass) {
        return new CsvToBeanBuilder(r)
                .withType(instanceClass)
                .withSeparator(',')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();
    }

    public static <T> StatefulBeanToCsv<T> createCsvWriter(Writer wr, ColumnPositionMappingStrategy<T> ms) {
        return new StatefulBeanToCsvBuilder(wr)
                .withMappingStrategy(ms)
                .withSeparator(',')
                .build();
    }

    public static Reader readFile(Path file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file.toString()));
    }

    /**
     * Assumes Csv file exists
     */
    public static <T> List<T> readFromCsvFile(Path file, Class<T> instanceClass) throws IOException {
        Reader reader = readFile(file);
        CsvToBean<T> csvReader = createCsvReader(reader, instanceClass);
        List<T> parsed = csvReader.parse();
        return parsed;
    }

    /**
     * Writes Java Beans to a Csv file.
     * Will create the file if it does not exist yet.
     */
    public static void writeToCsvFile(Path file, String[] header, List<String[]> data)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        FileUtil.createIfMissing(file);
        FileWriter writer = new FileWriter(file.toString());
        char sp = ',';
        char qc = CSVWriter.NO_QUOTE_CHARACTER;
        String le = CSVWriter.DEFAULT_LINE_END;
        char ec = '\\';
        CSVWriter csvWriter = new CSVWriter(writer, sp, qc, ec, le);
        csvWriter.writeNext(header);
        csvWriter.writeAll(data);
        csvWriter.close();
    }
}
