package taa.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import taa.commons.core.LogsCenter;
import taa.commons.exceptions.DataConversionException;
import taa.commons.exceptions.IllegalValueException;
import taa.commons.util.FileUtil;
import taa.commons.util.JsonUtil;

/**
 * A class to access ClassList data stored as a json file on the hard disk.
 */
public class JsonTaaStorage implements TaaStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaaStorage.class);

    private final Path filePath;

    public JsonTaaStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaaDataFilePath() {
        return filePath;
    }

    @Override
    public Optional<TaaData> readTaaData() throws DataConversionException {
        return readTaaData(filePath);
    }

    /**
     * Similar to {@link #readTaaData()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<TaaData> readTaaData(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaaData> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaaData.class);
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
    public void saveTaaData(TaaData taaData) throws IOException {
        saveTaaData(taaData, filePath);
    }

    /**
     * Similar to {@link #saveTaaData(TaaData)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaaData(TaaData taaData, Path filePath) throws IOException {
        requireNonNull(taaData);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaaData(taaData), filePath);
    }

}
