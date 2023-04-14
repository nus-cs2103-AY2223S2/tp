package seedu.ultron.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ultron.commons.core.LogsCenter;
import seedu.ultron.commons.exceptions.DataConversionException;
import seedu.ultron.commons.exceptions.IllegalValueException;
import seedu.ultron.commons.util.FileUtil;
import seedu.ultron.commons.util.JsonUtil;
import seedu.ultron.model.ReadOnlyUltron;


/**
 * A class to access Ultron data stored as a json file on the hard disk.
 */
public class JsonUltronStorage implements UltronStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUltronStorage.class);

    private Path filePath;

    public JsonUltronStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUltronFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUltron> readUltron() throws DataConversionException {
        return readUltron(filePath);
    }

    /**
     * Similar to {@link #readUltron()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUltron> readUltron(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUltron> jsonUltron = JsonUtil.readJsonFile(
                filePath, JsonSerializableUltron.class);
        if (!jsonUltron.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUltron.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUltron(ReadOnlyUltron ultron) throws IOException {
        saveUltron(ultron, filePath);
    }

    /**
     * Similar to {@link #saveUltron(ReadOnlyUltron)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUltron(ReadOnlyUltron ultron, Path filePath) throws IOException {
        requireNonNull(ultron);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUltron(ultron), filePath);
    }

}
