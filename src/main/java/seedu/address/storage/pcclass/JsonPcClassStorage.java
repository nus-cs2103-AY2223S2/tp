package seedu.address.storage.pcclass;

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
import seedu.address.model.person.ReadOnlyPcClass;

/**
 * A class handling PCClass Storage
 */
public class JsonPcClassStorage implements PcClassStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPcClassStorage.class);

    private Path filePath;

    public JsonPcClassStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPcFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPcClass> readPC() throws DataConversionException {
        return readPC(filePath);
    }

    /**
     * Similar to {@link #readPC()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPcClass> readPC(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePcClass> jsonPC = JsonUtil.readJsonFile(
                filePath, JsonSerializablePcClass.class);
        if (!jsonPC.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPC.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePC(ReadOnlyPcClass teaPet) throws IOException {
        savePC(teaPet, filePath);
    }

    /**
     * Similar to {@link #savePC(ReadOnlyPcClass)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePC(ReadOnlyPcClass readOnlyPcClass, Path filePath) throws IOException {
        requireNonNull(readOnlyPcClass);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePcClass(readOnlyPcClass), filePath);
    }
}
