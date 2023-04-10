package seedu.address.storage.tank.readings.ammonialevels;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;

/**
 * Represents a storage for FullReadingLevels.
 */
public interface FullReadingLevelsStorage {
    Path getFullReadingLevelsFilePath();

    Optional<ReadOnlyReadingLevels> readFullReadingLevels() throws DataConversionException,
            IOException;

    Optional<ReadOnlyReadingLevels> readFullReadingLevels(Path filePath)
            throws DataConversionException;

    void saveFullReadingLevels(ReadOnlyReadingLevels ammoniaLevels) throws IOException;

    void saveFullReadingLevels(ReadOnlyReadingLevels ammoniaLevels, Path filePath) throws IOException;


}
