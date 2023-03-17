package seedu.address.storage.pcclass;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.person.ReadOnlyPCClass;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonPCClassStorage implements PCClassStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPCClassStorage.class);

    private Path filePath;

    public JsonPCClassStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPCFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPCClass> readPC() throws DataConversionException {
        return readPC(filePath);
    }

    /**
     * Similar to {@link #readPC()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPCClass> readPC(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePCClass> jsonPC = JsonUtil.readJsonFile(
                filePath, JsonSerializablePCClass.class);
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
    public void savePC(ReadOnlyPCClass teaPet) throws IOException {
        savePC(teaPet, filePath);
    }

    /**
     * Similar to {@link #savePC(ReadOnlyPCClass)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePC(ReadOnlyPCClass readOnlyPCClass, Path filePath) throws IOException {
        requireNonNull(readOnlyPCClass);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePCClass(readOnlyPCClass), filePath);
    }
}
