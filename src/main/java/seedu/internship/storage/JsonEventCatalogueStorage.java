package seedu.internship.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.internship.commons.core.LogsCenter;
import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.commons.util.FileUtil;
import seedu.internship.commons.util.JsonUtil;
import seedu.internship.model.ReadOnlyEventCatalogue;

/**
 * A class to access EventCatalogue data stored as a json file on the hard disk.
 */
public class JsonEventCatalogueStorage implements EventCatalogueStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonEventCatalogueStorage.class);
    private Path filePath;

    public JsonEventCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEventCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEventCatalogue> readEventCatalogue() throws DataConversionException {
        return readEventCatalogue(filePath);
    }

    /**
     * Similar to {@link #readEventCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEventCatalogue> readEventCatalogue(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEventCatalogue> jsonEventCatalogue = JsonUtil.readJsonFile(
                filePath, JsonSerializableEventCatalogue.class);
        if (!jsonEventCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEventCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEventCatalogue(ReadOnlyEventCatalogue eventCatalogue) throws IOException {
        saveEventCatalogue(eventCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveEventCatalogue(ReadOnlyEventCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEventCatalogue(ReadOnlyEventCatalogue eventCatalogue, Path filePath)
            throws IOException {
        requireNonNull(eventCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEventCatalogue(eventCatalogue), filePath);
    }

}
