package seedu.dengue.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.commons.util.FileUtil;
import seedu.dengue.commons.util.CsvUtil;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;

/**
 * A class to access DengueHotspotTracker data stored as a csv file on the hard disk.
 */
public class CsvDengueHotspotStorage implements DengueHotspotStorage {

    private static final Logger logger = LogsCenter.getLogger(CsvDengueHotspotStorage.class);

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
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<CsvSerializableDengueHotspotTracker> csvDengueHotspotTracker = CsvUtil.readCsvFile(
                filePath, CsvSerializableDengueHotspotTracker.class);
        if (!csvDengueHotspotTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(csvDengueHotspotTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker) throws IOException {
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
        CsvUtil.saveCsvFile(new CsvSerializableDengueHotspotTracker(dengueHotspotTracker), filePath);
    }

}
