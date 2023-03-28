package seedu.dengue.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.commons.util.FileUtil;
import seedu.dengue.commons.util.JsonUtil;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;

/**
 * A class to access DengueHotspotTracker data stored as a json file on the hard disk.
 */
public class JsonDengueHotspotStorage implements DengueHotspotStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDengueHotspotStorage.class);

    private Path filePath;

    public JsonDengueHotspotStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDengueHotspotTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker() throws DataConversionException {
        return readDengueHotspotTracker(filePath);
    }

    /**
     * Similar to {@link #readDengueHotspotTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDengueHotspotTracker> jsonDengueHotspotTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableDengueHotspotTracker.class);
        if (!jsonDengueHotspotTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDengueHotspotTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker) throws IOException {
        saveDengueHotspotTracker(dengueHotspotTracker, filePath);
    }

    /**
     * Similar to {@link #saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, Path filePath)
            throws IOException {
        requireNonNull(dengueHotspotTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDengueHotspotTracker(dengueHotspotTracker), filePath);
    }

}
