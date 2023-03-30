package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * A class to access or store AddressBook data stored as a csv file on the hard disk.
 */
public class CsvAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(CsvAddressBookStorage.class);

    private Path filePath;

    public CsvAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(filePath);
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<List<List<String>>> csvData = CsvUtil.readCsvFile(
                filePath, CsvSerializableAddressBook.CSV_HEADERS);
        if (!csvData.isPresent()) {
            return Optional.empty();
        }

        try {
            List<List<String>> data = csvData.get();
            CsvSerializableAddressBook csvAddressBook = new CsvSerializableAddressBook(data);
            return Optional.of(csvAddressBook.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        CsvUtil.saveCsvFile(filePath, new CsvSerializableAddressBook(addressBook).toString());
    }

    public void saveAddressBook(ObservableList<Person> filteredList) throws IOException {
        saveAddressBook(filteredList, filePath);
    }

    /**
     * Saves a filtered list in a CSV file.
     *
     * @param filteredList The list containing only the filtered Persons.
     * @param filePath File path selected by user to save at.
     * @throws IOException
     */
    public void saveAddressBook(ObservableList<Person> filteredList, Path filePath) throws IOException {
        requireNonNull(filteredList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        CsvUtil.saveCsvFile(filePath, new CsvSerializableAddressBook(filteredList).toString());
    }
}
