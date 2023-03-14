package seedu.address.storage;

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
import seedu.address.model.ReadOnlyTracker;

/**
 * A class to access Tracker data stored as a json file on the hard disk.
 */
public class JsonTrackerStorage implements TrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackerStorage.class);

    private Path filePath;

    /**
     * Constructs a {@code JsonTrackerStorage}.
     *
     * @param filePath The path to the data file.
     */
    public JsonTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTracker> readTracker() throws DataConversionException, IOException {
        return readTracker(filePath);
    }

    @Override
    public Optional<ReadOnlyTracker> readTracker(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableTracker> jsonTracker =
                JsonUtil.readJsonFile(filePath, JsonSerializableTracker.class);
        if (!jsonTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTracker(ReadOnlyTracker tracker) throws IOException {
        saveTracker(tracker, filePath);
    }

    @Override
    public void saveTracker(ReadOnlyTracker tracker, Path filePath) throws IOException {
        requireAllNonNull(tracker, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTracker(tracker), filePath);
    }
}
