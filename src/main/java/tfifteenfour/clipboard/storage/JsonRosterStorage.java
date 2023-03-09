package tfifteenfour.clipboard.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.commons.util.FileUtil;
import tfifteenfour.clipboard.commons.util.JsonUtil;
import tfifteenfour.clipboard.model.ReadOnlyRoster;

/**
 * A class to access Roster data stored as a json file on the hard disk.
 */
public class JsonRosterStorage implements RosterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRosterStorage.class);

    private Path filePath;

    public JsonRosterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRosterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRoster> readRoster() throws DataConversionException {
        return readRoster(filePath);
    }

    /**
     * Similar to {@link #readRoster()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRoster> readRoster(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRoster> jsonRoster = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoster.class);
        if (!jsonRoster.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRoster.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRoster(ReadOnlyRoster addressBook) throws IOException {
        saveRoster(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveRoster(ReadOnlyRoster)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRoster(ReadOnlyRoster addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRoster(addressBook), filePath);
    }

}
