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

    /**
     * Returns an {@code Optional<String>} after reading the .txt file from the given {@code Path} filePath.
     *
     * @param filePath The path to .txt file.
     * @return An {@code Optional<String>} Object reading from the .txt file.
     */
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

    /**
     * Saves the argument string to the file identified with the given {@code filePath}.
     *
     * @param string The string to replace content in .txt file.
     * @param filePath The path to .txt file.
     * @throws IOException If the writing process have errors.
     */
    public static void saveTxtFile(String string, Path filePath) throws IOException {
        requireNonNull(filePath);
        requireNonNull(string);

        FileUtil.writeToFile(filePath, string);
    }

}
