package expresslibrary.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import expresslibrary.commons.core.LogsCenter;
import expresslibrary.commons.exceptions.DataConversionException;
import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.commons.util.FileUtil;
import expresslibrary.commons.util.JsonUtil;
import expresslibrary.model.ReadOnlyExpressLibrary;

/**
 * A class to access ExpressLibrary data stored as a json file on the hard disk.
 */
public class JsonExpressLibraryStorage implements ExpressLibraryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonExpressLibraryStorage.class);

    private Path filePath;

    public JsonExpressLibraryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getExpressLibraryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyExpressLibrary> readExpressLibrary() throws DataConversionException {
        return readExpressLibrary(filePath);
    }

    /**
     * Similar to {@link #readExpressLibrary()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyExpressLibrary> readExpressLibrary(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableExpressLibrary> jsonExpressLibrary = JsonUtil.readJsonFile(
                filePath, JsonSerializableExpressLibrary.class);
        if (!jsonExpressLibrary.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonExpressLibrary.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary) throws IOException {
        saveExpressLibrary(expressLibrary, filePath);
    }

    /**
     * Similar to {@link #saveExpressLibrary(ReadOnlyExpressLibrary)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary, Path filePath) throws IOException {
        requireNonNull(expressLibrary);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableExpressLibrary(expressLibrary), filePath);
    }

}
