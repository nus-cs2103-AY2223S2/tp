package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * Converts a Java object instance to CSV and vice versa.
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);

    /**
     * Returns the csv data from the given file or {@code Optional.empty()} object if the file is not found.
     * If any values are missing from the file, default values will be used, as long as the file is a valid csv file.
     * @param filePath cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public static Optional<List<List<String>>> readCsvFile(Path filePath) throws DataConversionException {
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

        return Optional.of(tokenizeCsv(csvData));
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

    /**
     * Converts a String into a csv-friendly String.
     * @param str String to be converted.
     * @return The csv-friendly String.
     */
    public static String toCsvField(String str) {
        if (str.contains(",")) {
            str = str.replaceAll("\"", "\"\"");
            str = "\"" + str + "\"";
        }
        return str;
    }

    /**
     * Tokenizes a csv String.
     * Each line will be tokenized into its own list.
     * @param str Line to be tokenized.
     * @return The list of list of tokens.
     */
    public static List<List<String>> tokenizeCsv(String str) {
        List<List<String>> listOfTokens = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new StringReader(str))) {
            // Ignore header line
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Append a comma at the end so that the line is easier to tokenize
                line += ",";
                List<String> tokens = new ArrayList<>();
                int startIdx = 0;
                int endIdx = 0;
                int trailingQuoteCount = 0;
                boolean isStartWithQuote = false;
                boolean isBreakpoint = true;

                for (int i = 0; i < line.length(); ++i) {
                    char c = line.charAt(i);

                    if (isBreakpoint) {
                        isBreakpoint = false;
                        startIdx = i;
                        if (c == '"') {
                            // Token starts with '"' so the actual text starts on next character
                            isStartWithQuote = true;
                            ++startIdx;
                        } else {
                            isStartWithQuote = false;
                        }

                    }

                    if (c == ',') {
                        if (!isStartWithQuote) {
                            // If it is not a special token that starts with '"', then comma must mean end of token
                            endIdx = i;
                            String token = line.substring(startIdx, endIdx);
                            if (!token.isBlank()) {
                                tokens.add(token);
                            }
                            isBreakpoint = true;
                        } else if (trailingQuoteCount % 2 == 1) {
                            // Otherwise, odd number of trailing quotation marks implies it is end of token
                            endIdx = i - 1;
                            String token = line.substring(startIdx, endIdx).replaceAll("\"\"", "\"");
                            if (!token.isBlank()) {
                                tokens.add(token);
                            }
                            isBreakpoint = true;
                        }
                        trailingQuoteCount = 0;
                    } else if (c == '"') {
                        ++trailingQuoteCount;
                    } else {
                        trailingQuoteCount = 0;
                    }
                }

                listOfTokens.add(tokens);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfTokens;
    }

}
