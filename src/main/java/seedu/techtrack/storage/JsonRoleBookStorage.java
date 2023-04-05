package seedu.techtrack.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.techtrack.commons.core.LogsCenter;
import seedu.techtrack.commons.exceptions.DataConversionException;
import seedu.techtrack.commons.exceptions.IllegalValueException;
import seedu.techtrack.commons.util.FileUtil;
import seedu.techtrack.commons.util.JsonUtil;
import seedu.techtrack.model.ReadOnlyRoleBook;

/**
 * A class to access RoleBook data stored as a json file on the hard disk.
 */
public class JsonRoleBookStorage implements RoleBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRoleBookStorage.class);

    private Path filePath;

    public JsonRoleBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRoleBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRoleBook> readRoleBook() throws DataConversionException {
        return readRoleBook(filePath);
    }

    /**
     * Similar to {@link #readRoleBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRoleBook> readRoleBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRoleBook> jsonRoleBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoleBook.class);
        if (!jsonRoleBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRoleBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRoleBook(ReadOnlyRoleBook roleBook) throws IOException {
        saveRoleBook(roleBook, filePath);
    }

    /**
     * Similar to {@link #saveRoleBook(ReadOnlyRoleBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRoleBook(ReadOnlyRoleBook roleBook, Path filePath) throws IOException {
        requireNonNull(roleBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRoleBook(roleBook), filePath);
    }

}
