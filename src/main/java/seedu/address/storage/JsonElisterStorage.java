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
import seedu.address.model.ReadOnlyElister;

/**
 * A class to access Elister data stored as a json file on the hard disk.
 */
public class JsonElisterStorage implements ElisterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonElisterStorage.class);

    private Path filePath;

    public JsonElisterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getElisterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyElister> readElister() throws DataConversionException {
        return readElister(filePath);
    }

    /**
     * Similar to {@link #readElister()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyElister> readElister(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableElister> jsonElister = JsonUtil.readJsonFile(
                filePath, JsonSerializableElister.class);
        if (!jsonElister.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonElister.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveElister(ReadOnlyElister elister) throws IOException {
        saveElister(elister, filePath);
    }

    /**
     * Similar to {@link #saveElister(ReadOnlyElister)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveElister(ReadOnlyElister elister, Path filePath) throws IOException {
        requireNonNull(elister);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableElister(elister), filePath);
    }

}
