package seedu.connectus.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.connectus.commons.core.LogsCenter;
import seedu.connectus.commons.exceptions.DataConversionException;
import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.commons.util.FileUtil;
import seedu.connectus.commons.util.JsonUtil;
import seedu.connectus.model.ReadOnlyConnectUs;

/**
 * A class to access ConnectUS data stored as a json file on the hard disk.
 */
public class JsonConnectUsStorage implements ConnectUsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonConnectUsStorage.class);

    private Path filePath;

    public JsonConnectUsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getConnectUsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyConnectUs> readConnectUs() throws DataConversionException {
        return readConnectUs(filePath);
    }

    /**
     * Similar to {@link #readConnectUs()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyConnectUs> readConnectUs(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableConnectUs> jsonConnectUs = JsonUtil.readJsonFile(
                filePath, JsonSerializableConnectUs.class);
        if (!jsonConnectUs.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonConnectUs.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveConnectUs(ReadOnlyConnectUs connectUs) throws IOException {
        saveConnectUs(connectUs, filePath);
    }

    /**
     * Similar to {@link #saveConnectUs(ReadOnlyConnectUs)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveConnectUs(ReadOnlyConnectUs connectUs, Path filePath) throws IOException {
        requireNonNull(connectUs);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableConnectUs(connectUs), filePath);
    }

}
