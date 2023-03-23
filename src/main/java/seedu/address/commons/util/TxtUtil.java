package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Encapsulates the reading and writing executed user input commands to/from .txt file
 */
public class TxtUtil {
    private static final Logger logger = LogsCenter.getLogger(TxtUtil.class);

    public static Optional<String> readTxtFile(Path filePath) {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("Txt file " + filePath + " not found");
            return Optional.empty();
        }

        String historyString = null;

        try {
            historyString = FileUtil.readFromFile(filePath);
        } catch (IOException e) {
            logger.warning("Error reading from jsonFile " + filePath + ": " + e);
        }

        return Optional.of(historyString);
    }

    public static void saveTxtFile(String historyString, Path filePath) throws IOException{
        requireNonNull(filePath);
        requireNonNull(historyString);

        FileUtil.writeToFile(filePath, historyString);
    }
}
