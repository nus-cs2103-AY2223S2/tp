package codoc.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import codoc.commons.exceptions.DataConversionException;
import codoc.commons.exceptions.IllegalValueException;
import codoc.commons.util.FileUtil;
import codoc.commons.util.JsonUtil;
import codoc.model.ReadOnlyCodoc;

/**
 * A class to access Codoc data stored as a json file on the hard disk.
 */
public class JsonCodocStorage implements CodocStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCodocStorage.class);

    private Path filePath;

    public JsonCodocStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCodocFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCodoc> readCodoc() throws DataConversionException {
        return readCodoc(filePath);
    }

    /**
     * Similar to {@link #readCodoc()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCodoc> readCodoc(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCodoc> jsonCodoc = JsonUtil.readJsonFile(
                filePath, JsonSerializableCodoc.class);
        if (!jsonCodoc.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCodoc.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCodoc(ReadOnlyCodoc codoc) throws IOException {
        saveCodoc(codoc, filePath);
    }

    /**
     * Similar to {@link #saveCodoc(ReadOnlyCodoc)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCodoc(ReadOnlyCodoc codoc, Path filePath) throws IOException {
        requireNonNull(codoc);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCodoc(codoc), filePath);
    }

}
