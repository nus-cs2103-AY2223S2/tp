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
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;
    private Path exportFilePath;

    /**
     * Constructs a {@code JsonAddressBookStorage} with the given {@code filePath and exportFilePath}.
     */
    public JsonAddressBookStorage(Path filePath, Path exportFilePath) {
        this.filePath = filePath;
        this.exportFilePath = exportFilePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    public Path getExportFilePath() {
        return exportFilePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
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
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    @Override
    public void exportPerson(Person personToExport) throws IOException {
        exportPerson(personToExport, exportFilePath);
    }

    /**
     * Similar to {@link #exportPerson(Person)}.
     *
     * @param exportFilePath location of the data. Cannot be null.
     */
    public void exportPerson(Person personToExport, Path exportFilePath) throws IOException {
        requireNonNull(personToExport);
        requireNonNull(exportFilePath);

        FileUtil.createIfMissing(exportFilePath);
        AddressBook exportAddressBook = new AddressBook();
        exportAddressBook.addPerson(personToExport);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(exportAddressBook), exportFilePath);
    }
}
