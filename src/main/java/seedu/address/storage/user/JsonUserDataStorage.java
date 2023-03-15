package seedu.address.storage.user;

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
import seedu.address.model.ReadOnlyUserData;
import seedu.address.storage.DataStorage;

/**
 * A class to access User data stored as a json file on the hard disk.
 * Todo: Abstract out common logic between JsonUserDataStorage and AddressBookStorage.
 */
public class JsonUserDataStorage extends DataStorage<ReadOnlyUserData> {

    private static final Logger logger = LogsCenter.getLogger(JsonUserDataStorage.class);

    public JsonUserDataStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Similar to {@link #readData()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyUserData> readData(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUserData> jsonUserData = JsonUtil.readJsonFile(
                filePath, JsonSerializableUserData.class);
        if (!jsonUserData.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUserData.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Similar to {@link #saveData(ReadOnlyUserData)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveData(ReadOnlyUserData userData, Path filePath) throws IOException {
        requireNonNull(userData);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUserData(userData), filePath);
    }
}
