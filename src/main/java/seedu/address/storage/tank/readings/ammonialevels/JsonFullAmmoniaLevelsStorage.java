package seedu.address.storage.tank.readings.ammonialevels;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.tank.readings.ReadOnlyAmmoniaLevels;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A class to access {@code FullAmmoniaLevels} data stored as a json file on the hard disk.
 */
public class JsonFullAmmoniaLevelsStorage implements FullAmmoniaLevelsStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonFullAmmoniaLevelsStorage.class);

    private final Path filePath;

    public JsonFullAmmoniaLevelsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFullAmmoniaLevelsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAmmoniaLevels> readFullAmmoniaLevels() throws DataConversionException {
        return readFullAmmoniaLevels(filePath);
    }

    /**
     * Returns {@code FullAmmoniaLevels} data as a {@link ReadOnlyAmmoniaLevels}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param filePath The path of the {@code FullAmmoniaLevels} data file.
     * @throws DataConversionException If the data in storage is not in the expected format.
     */
    @Override
    public Optional<ReadOnlyAmmoniaLevels> readFullAmmoniaLevels(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFullAmmoniaLevels> jsonFullAmmoniaLevels = JsonUtil.readJsonFile(
                filePath, JsonSerializableFullAmmoniaLevels.class);
        if (jsonFullAmmoniaLevels.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFullAmmoniaLevels.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels ammoniaLevels) throws IOException {
        saveFullAmmoniaLevels(ammoniaLevels, filePath);
    }

    /**
     * Similar to {@link #saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels)}.
     *
     * @param filePath Location of the data. Cannot be null.
     */
    @Override
    public void saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels ammoniaLevels, Path filePath) throws IOException {
        requireAllNonNull(ammoniaLevels, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFullAmmoniaLevels(ammoniaLevels), filePath);
    }
}
