package trackr.storage;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import trackr.commons.core.LogsCenter;
import trackr.commons.exceptions.DataConversionException;
import trackr.commons.exceptions.IllegalValueException;
import trackr.commons.util.FileUtil;
import trackr.commons.util.JsonUtil;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;

/**
 * A class to access Trackr data stored as a json file on the hard disk.
 */
public class JsonTrackrStorage implements TrackrStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackrStorage.class);

    private Path filePath;

    public JsonTrackrStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackrFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySupplierList> readSupplierList() throws DataConversionException {
        return readSupplierList(filePath);
    }

    /**
     * Similar to {@link #readSupplierList}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySupplierList> readSupplierList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrackr> jsonTrackr = JsonUtil.readJsonFile(
                filePath, JsonSerializableTrackr.class);
        if (!jsonTrackr.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackr.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrackr> jsonTrackr = JsonUtil.readJsonFile(
                filePath, JsonSerializableTrackr.class);
        if (!jsonTrackr.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackr.get().toTaskModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public Optional<ReadOnlyOrderList> readOrderList() throws DataConversionException {
        return readOrderList(filePath);
    }

    /**
     * Similar to {@link #readOrderList}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyOrderList> readOrderList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrackr> jsonTrackr = JsonUtil.readJsonFile(
                filePath, JsonSerializableTrackr.class);
        if (!jsonTrackr.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackr.get().toOrderModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList) throws IOException {
        saveTrackr(addressBook, taskList, orderList, filePath);
    }

    @Override
    public void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList, Path filePath)
            throws IOException {
        requireAllNonNull(addressBook, taskList, orderList, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrackr(addressBook, taskList, orderList), filePath);
    }

}
