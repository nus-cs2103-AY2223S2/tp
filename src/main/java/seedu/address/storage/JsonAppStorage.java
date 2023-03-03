package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

/**
 * An abstract AppStorage that implements common read operations from files.
 *
 * @param <T> The ReadOnlyEntity
 * @param <K> FriendlyLink
 * @param <R> Any JsonSerializableObjects
 */
abstract class JsonAppStorage<T, K extends T, R extends JsonSerializable<K>> {

    private final Path filePath;

    protected JsonAppStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public Optional<T> read(Class<R> classInfo, Logger logger) throws DataConversionException {
        return read(filePath, classInfo, logger);
    }

    /**
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<T> read(Path filePath, Class<R> classInfo, Logger logger)
            throws DataConversionException {

        requireNonNull(filePath);

        Optional<R> jsonStorage = JsonUtil.readJsonFile(filePath, classInfo);
        if (jsonStorage.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStorage.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }
}
