package seedu.address.storage.parents;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.person.parent.ReadOnlyParents;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access Parents data stored as a json file on the hard disk.
 */
public class JsonParentsStorage implements ParentsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonParentsStorage.class);

    private Path filePath;

    public JsonParentsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getParentsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyParents> readParents() throws DataConversionException {
        return readParents(filePath);
    }

    /**
     * Similar to {@link #readParents()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyParents> readParents(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableParents> jsonParents = JsonUtil.readJsonFile(
                filePath, JsonSerializableParents.class);
        if (!jsonParents.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonParents.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveParents(ReadOnlyParents readOnlyParents) throws IOException {
        saveParents(readOnlyParents, filePath);
    }

    /**
     * Similar to {@link #saveParents(ReadOnlyParents)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveParents(ReadOnlyParents readOnlyParents, Path filePath) throws IOException {
        requireNonNull(readOnlyParents);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableParents(readOnlyParents), filePath);
    }
}

