package seedu.address.storage.elderly;

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
import seedu.address.model.ReadOnlyElderly;

/**
 * A class to access elderly data stored as a json file on the hard disk.
 */
public class JsonElderlyStorage implements ElderlyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonElderlyStorage.class);

    private final Path filePath;

    public JsonElderlyStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getElderlyFilePath() {
        return filePath;
    }


    @Override
    public Optional<ReadOnlyElderly> readElderly() throws DataConversionException {
        return readElderly(filePath);
    }

    /**
     * Similar to {@link #readElderly()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyElderly> readElderly(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableElderly> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableElderly.class);

        if (jsonAddressBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveElderly(ReadOnlyElderly friendlyLink) throws IOException {
        saveElderly(friendlyLink, filePath);
    }

    /**
     * Similar to {@link #saveElderly(ReadOnlyElderly)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveElderly(ReadOnlyElderly friendlyLink, Path filePath) throws IOException {
        requireNonNull(friendlyLink);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableElderly(friendlyLink), filePath);
    }
}
