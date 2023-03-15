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
import seedu.address.model.ReadOnlyTankList;

/**
 * A class to access {@code TankList} data stored as a json file on the hard disk.
 */
public class JsonTankListStorage implements TankListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTankListStorage.class);

    private final Path filePath;

    public JsonTankListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTankListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTankList> readTankList() throws DataConversionException {
        return readTankList(filePath);
    }

    /**
     * Returns {@code TankList} data as a {@link ReadOnlyTankList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param filePath The path of the {@code TankList} data file.
     * @throws DataConversionException If the data in storage is not in the expected format.
     */
    @Override
    public Optional<ReadOnlyTankList> readTankList(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTankList> jsonTankList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTankList.class);
        if (jsonTankList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTankList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTankList(ReadOnlyTankList tankList) throws IOException {
        saveTankList(tankList, filePath);
    }

    /**
     * Similar to {@link #saveTankList(ReadOnlyTankList)}.
     *
     * @param filePath Location of the data. Cannot be null.
     */
    @Override
    public void saveTankList(ReadOnlyTankList tankList, Path filePath) throws IOException {
        requireAllNonNull(tankList, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTankList(tankList), filePath);
    }

}
