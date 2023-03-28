package seedu.address.storage.tank.readings.ammonialevels;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.tank.readings.ReadOnlyAmmoniaLevels;

/**
 * Represents a storage for FullAmmoniaLevels.
 */
public interface FullAmmoniaLevelsStorage {
    Path getFullAmmoniaLevelsFilePath();

    Optional<ReadOnlyAmmoniaLevels> readFullAmmoniaLevels() throws DataConversionException,
            IOException;

    Optional<ReadOnlyAmmoniaLevels> readFullAmmoniaLevels(Path filePath)
            throws DataConversionException;

    void saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels ammoniaLevels) throws IOException;

    void saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels ammoniaLevels, Path filePath) throws IOException;


}
