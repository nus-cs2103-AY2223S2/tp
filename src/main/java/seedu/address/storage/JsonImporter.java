package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.exceptions.JsonNotFoundException;

/**
 * Implementation of an importer that reads AddressBook data from a given file.
 */
public class JsonImporter implements Importer {

    private static final Logger logger = LogsCenter.getLogger(JsonImporter.class);
    private Path filePath;

    public JsonImporter(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the AddressBook data from the given file.
     * @return An {@code Optional} containing a {@code ReadOnlyAddressBook}.
     * @throws DataConversionException When there are illegal values in the stored data.
     */
    public ReadOnlyAddressBook readData() throws DataConversionException, JsonNotFoundException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (jsonAddressBook.isEmpty()) {
            throw new JsonNotFoundException("No file found!");
        }

        try {
            return jsonAddressBook.get().toModelType();
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }
}
