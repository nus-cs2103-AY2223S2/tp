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
import seedu.address.model.ReadOnlyElister;
import seedu.address.model.person.Person;

/**
 * A class to access or store E-lister data stored as a csv file on the hard disk.
 */
public class CsvElisterStorage implements ElisterStorage {

    private static final Logger logger = LogsCenter.getLogger(CsvElisterStorage.class);

    private Path filePath;

    public CsvElisterStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getElisterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyElister> readElister() throws DataConversionException, IOException {
        return readElister(filePath);
    }

    @Override
    public Optional<ReadOnlyElister> readElister(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<List<List<String>>> csvData = CsvUtil.readCsvFile(
                filePath, CsvSerializableElister.CSV_HEADERS);
        if (!csvData.isPresent()) {
            return Optional.empty();
        }

        try {
            List<List<String>> data = csvData.get();
            CsvSerializableElister csvElister = new CsvSerializableElister(data);
            return Optional.of(csvElister.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveElister(ReadOnlyElister elister) throws IOException {
        saveElister(elister, filePath);
    }

    @Override
    public void saveElister(ReadOnlyElister elister, Path filePath) throws IOException {
        requireNonNull(elister);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        CsvUtil.saveCsvFile(filePath, new CsvSerializableElister(elister).toString());
    }

    public void saveElister(ObservableList<Person> filteredList) throws IOException {
        saveElister(filteredList, filePath);
    }

    /**
     * Saves a filtered list in a CSV file.
     *
     * @param filteredList The list containing only the filtered Persons.
     * @param filePath File path selected by user to save at.
     * @throws IOException
     */
    public void saveElister(ObservableList<Person> filteredList, Path filePath) throws IOException {
        requireNonNull(filteredList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        CsvUtil.saveCsvFile(filePath, new CsvSerializableElister(filteredList).toString());
    }
}
