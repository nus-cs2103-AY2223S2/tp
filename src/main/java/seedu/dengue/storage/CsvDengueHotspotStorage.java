package seedu.dengue.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.commons.util.CsvUtil;
import seedu.dengue.commons.util.FileUtil;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.exceptions.PersonHasNullAttributesException;

/**
 * A class to access DengueHotspotTracker data stored as a csv file on the hard disk.
 */
public class CsvDengueHotspotStorage implements DengueHotspotStorage {

    private static final Logger logger = LogsCenter.getLogger(CsvDengueHotspotStorage.class);

    private static String[] headers = Person.getHeaders();
    private Path filePath;

    public CsvDengueHotspotStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDengueHotspotTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker() throws DataConversionException {
        return readDengueHotspotTracker(filePath);
    }

    /**
     * Similar to {@link #readDengueHotspotTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker(Path filePath)
            throws DataConversionException, PersonHasNullAttributesException {
        requireNonNull(filePath);
        try {
            Optional<List<Person>> optionalList = CsvUtil.readCsvFile(
                    filePath, Person.class);
            if (!optionalList.isPresent()) {
                return Optional.empty();
            }
            checkNonNullPersons(optionalList.get());
            DengueHotspotTracker readOnly = new DengueHotspotTracker();
            readOnly.setPersons(optionalList.get());
            return Optional.of(readOnly);
        } catch (DataConversionException | PersonHasNullAttributesException e) {
            throw e;
        }
    }

    /**
     * Check if any of the persons in personList contains null attributes
     * @param personList takes in personList
     * @throws PersonHasNullAttributesException if any person has null attributes
     */
    public void checkNonNullPersons(List<Person> personList) throws PersonHasNullAttributesException {
        for (Person person : personList) {
            person.isNonNullAttributesPerson();
        }
    }

    @Override
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker)
            throws IOException {
        saveDengueHotspotTracker(dengueHotspotTracker, filePath);
    }

    /**
     * Similar to {@link #saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, Path filePath)
            throws IOException {
        requireNonNull(dengueHotspotTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        CsvUtil.saveCsvFile(dengueHotspotTracker.getPersonList()
                                    .stream()
                                    .map(Person::toCsvString)
                                    .collect(Collectors.toList()), filePath, headers);
    }
}
