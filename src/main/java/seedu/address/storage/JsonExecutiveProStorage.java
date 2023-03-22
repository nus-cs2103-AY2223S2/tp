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
import seedu.address.model.ReadOnlyExecutiveProDb;

/**
 * A class to access ExecutiveProDb data stored as a json file on the hard disk.
 */
public class JsonExecutiveProStorage implements ExecutiveProStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonExecutiveProStorage.class);

    private Path filePath;

    public JsonExecutiveProStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getExecutiveProDbFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyExecutiveProDb> readExecutiveProDb() throws DataConversionException {
        return readExecutiveProDb(filePath);
    }

    /**
     * Similar to {@link #readExecutiveProDb()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyExecutiveProDb> readExecutiveProDb(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableExecutiveProDb> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableExecutiveProDb.class);
        if (!jsonAddressBook.isPresent()) {
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
    public void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb) throws IOException {
        saveExecutiveProDb(executiveProDb, filePath);
    }

    /**
     * Similar to {@link #saveExecutiveProDb(ReadOnlyExecutiveProDb)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb, Path filePath) throws IOException {
        requireNonNull(executiveProDb);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableExecutiveProDb(executiveProDb), filePath);
    }

}
