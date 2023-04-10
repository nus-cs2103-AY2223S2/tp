package teambuilder.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import teambuilder.commons.core.LogsCenter;
import teambuilder.commons.exceptions.DataConversionException;
import teambuilder.commons.exceptions.IllegalValueException;
import teambuilder.commons.util.FileUtil;
import teambuilder.commons.util.JsonUtil;
import teambuilder.model.ReadOnlyTeamBuilder;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonTeamBuilderStorage implements TeamBuilderStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeamBuilderStorage.class);

    private Path filePath;

    public JsonTeamBuilderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTeamBuilder> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTeamBuilder> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTeamBuilder> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTeamBuilder.class);
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
    public void saveAddressBook(ReadOnlyTeamBuilder addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyTeamBuilder)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyTeamBuilder addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTeamBuilder(addressBook), filePath);
    }

}
