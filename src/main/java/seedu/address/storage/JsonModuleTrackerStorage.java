package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyModuleTracker;

/**
 * A class to access ModuleTracker data stored as a json file on the hard disk.
 */
public class JsonModuleTrackerStorage implements ModuleTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleTrackerStorage.class);

    private Path filePath;

    public JsonModuleTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModuleTracker> readModuleTracker() throws DataConversionException {
        return readModuleTracker(filePath);
    }

    /**
     * Similar to {@link #readModuleTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModuleTracker> readModuleTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleTracker> jsonModuleTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleTracker.class);
        if (!jsonModuleTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonModuleTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker) throws IOException {
        saveModuleTracker(moduleTracker, filePath);
    }

    /**
     * Similar to {@link #saveModuleTracker(ReadOnlyModuleTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker, Path filePath) throws IOException {
        requireNonNull(moduleTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleTracker(moduleTracker), filePath);
    }

}
