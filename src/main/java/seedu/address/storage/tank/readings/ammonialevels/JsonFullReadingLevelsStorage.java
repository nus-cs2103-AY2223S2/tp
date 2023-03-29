package seedu.address.storage.tank.readings.ammonialevels;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;

/**
 * A class to access {@code FullReadingLevels} data stored as a json file on the hard disk.
 */
public class JsonFullReadingLevelsStorage implements FullReadingLevelsStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonFullReadingLevelsStorage.class);

    private final Path filePath;

    public JsonFullReadingLevelsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFullReadingLevelsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyReadingLevels> readFullReadingLevels() throws DataConversionException {
        return readFullReadingLevels(filePath);
    }

    /**
     * Returns {@code FullReadingLevels} data as a {@link ReadOnlyReadingLevels}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param filePath The path of the {@code FullReadingLevels} data file.
     * @throws DataConversionException If the data in storage is not in the expected format.
     */
    @Override
    public Optional<ReadOnlyReadingLevels> readFullReadingLevels(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFullReadingLevels> jsonFullReadingLevels = JsonUtil.readJsonFile(
                filePath, JsonSerializableFullReadingLevels.class);
        if (jsonFullReadingLevels.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFullReadingLevels.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFullReadingLevels(ReadOnlyReadingLevels readingLevels) throws IOException {
        saveFullReadingLevels(readingLevels, filePath);
    }

    /**
     * Similar to {@link #saveFullReadingLevels(ReadOnlyReadingLevels)}.
     *
     * @param filePath Location of the data. Cannot be null.
     */
    @Override
    public void saveFullReadingLevels(ReadOnlyReadingLevels readingLevels, Path filePath) throws IOException {
        requireAllNonNull(readingLevels, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFullReadingLevels(readingLevels), filePath);
    }
}
