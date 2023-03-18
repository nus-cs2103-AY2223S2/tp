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
import seedu.address.model.ReadOnlyMasterDeck;

/**
 * A class to access MasterDeck data stored as a json file on the hard disk.
 */
public class JsonMasterDeckStorage implements MasterDeckStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMasterDeckStorage.class);

    private final Path filePath;

    public JsonMasterDeckStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMasterDeckFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMasterDeck> readMasterDeck() throws DataConversionException {
        return readMasterDeck(filePath);
    }

    /**
     * Similar to {@link #readMasterDeck()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMasterDeck> readMasterDeck(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMasterDeck> jsonMasterDeck = JsonUtil.readJsonFile(
                filePath, JsonSerializableMasterDeck.class);
        if (jsonMasterDeck.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMasterDeck.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMasterDeck(ReadOnlyMasterDeck masterDeck) throws IOException {
        saveMasterDeck(masterDeck, filePath);
    }

    /**
     * Similar to {@link #saveMasterDeck(ReadOnlyMasterDeck)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMasterDeck(ReadOnlyMasterDeck masterDeck, Path filePath) throws IOException {
        requireNonNull(masterDeck);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMasterDeck(masterDeck), filePath);
    }

}
