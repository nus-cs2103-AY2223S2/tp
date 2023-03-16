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
import seedu.address.model.ReadOnlyInternBuddy;

/**
 * A class to access InternBuddy data stored as a json file on the hard disk.
 */
public class JsonInternBuddyStorage implements InternBuddyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternBuddyStorage.class);

    private Path filePath;

    public JsonInternBuddyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternBuddyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternBuddy> readInternBuddy() throws DataConversionException {
        return readInternBuddy(filePath);
    }

    /**
     * Similar to {@link #readInternBuddy()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInternBuddy> readInternBuddy(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternBuddy> jsonInternBuddy = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternBuddy.class);
        if (!jsonInternBuddy.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternBuddy.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInternBuddy(ReadOnlyInternBuddy internBuddy) throws IOException {
        saveInternBuddy(internBuddy, filePath);
    }

    /**
     * Similar to {@link #saveInternBuddy(ReadOnlyInternBuddy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternBuddy(ReadOnlyInternBuddy internBuddy, Path filePath) throws IOException {
        requireNonNull(internBuddy);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternBuddy(internBuddy), filePath);
    }

}
