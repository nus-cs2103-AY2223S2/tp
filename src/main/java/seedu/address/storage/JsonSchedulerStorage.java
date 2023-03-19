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
import seedu.address.model.ReadOnlyScheduler;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonSchedulerStorage implements SchedulerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSchedulerStorage.class);

    private final Path filePath;

    public JsonSchedulerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSchedulerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyScheduler> readScheduler() throws DataConversionException {
        return readScheduler(filePath);
    }

    /**
     * Similar to {@link #readScheduler()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyScheduler> readScheduler(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableScheduler> jsonScheduler = JsonUtil.readJsonFile(
            filePath, JsonSerializableScheduler.class);
        if (!jsonScheduler.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonScheduler.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveScheduler(ReadOnlyScheduler scheduler) throws IOException {
        saveScheduler(scheduler, filePath);
    }

    /**
     * Similar to {@link #saveScheduler(ReadOnlyScheduler)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveScheduler(ReadOnlyScheduler scheduler, Path filePath) throws IOException {
        requireNonNull(scheduler);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableScheduler(scheduler), filePath);
    }
}
