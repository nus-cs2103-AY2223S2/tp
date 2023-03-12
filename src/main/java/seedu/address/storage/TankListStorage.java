package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTankList;

/**
 * Represents a storage for Tank List.
 */
public interface TankListStorage {
    Path getTankListFilePath();

    Optional<ReadOnlyTankList> readTankList() throws DataConversionException,
            IOException;

    Optional<ReadOnlyTankList> readTankList(Path filePath)
            throws DataConversionException, IOException;

    void saveTankList(ReadOnlyTankList tankList) throws IOException;

    void saveTankList(ReadOnlyTankList tankList, Path filePath) throws IOException;

}
