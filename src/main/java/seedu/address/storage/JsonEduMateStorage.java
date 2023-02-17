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
import seedu.address.model.ReadOnlyEduMate;

/**
 * A class to access EduMate data stored as a json file on the hard disk.
 */
public class JsonEduMateStorage implements EduMateStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEduMateStorage.class);

    private Path filePath;

    public JsonEduMateStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEduMateFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEduMate> readEduMate() throws DataConversionException {
        return readEduMate(filePath);
    }

    /**
     * Similar to {@link #readEduMate()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEduMate> readEduMate(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEduMate> jsonEduMate = JsonUtil.readJsonFile(
                filePath, JsonSerializableEduMate.class);
        if (!jsonEduMate.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEduMate.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEduMate(ReadOnlyEduMate eduMate) throws IOException {
        saveEduMate(eduMate, filePath);
    }

    /**
     * Similar to {@link #saveEduMate(ReadOnlyEduMate)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEduMate(ReadOnlyEduMate eduMate, Path filePath) throws IOException {
        requireNonNull(eduMate);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEduMate(eduMate), filePath);
    }

}
