package seedu.wife.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.wife.commons.core.LogsCenter;
import seedu.wife.commons.exceptions.DataConversionException;
import seedu.wife.commons.exceptions.IllegalValueException;
import seedu.wife.commons.util.FileUtil;
import seedu.wife.commons.util.JsonUtil;
import seedu.wife.model.ReadOnlyWife;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonWifeStorage implements WifeStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWifeStorage.class);

    private Path filePath;

    public JsonWifeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWifeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWife> readWife() throws DataConversionException {
        return readWife(filePath);
    }

    /**
     * Similar to {@link #readWife()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyWife> readWife(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableWife> jsonWife = JsonUtil.readJsonFile(
                filePath, JsonSerializableWife.class);
        if (!jsonWife.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWife.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveWife(ReadOnlyWife wife) throws IOException {
        saveWife(wife, filePath);
    }

    /**
     * Similar to {@link #saveWife(ReadOnlyWife)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWife(ReadOnlyWife wife, Path filePath) throws IOException {
        requireNonNull(wife);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWife(wife), filePath);
    }

}
