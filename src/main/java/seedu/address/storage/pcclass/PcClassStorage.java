package seedu.address.storage.pcclass;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.person.ReadOnlyPcClass;

/**
 * A Interface for PCClassStorage
 */
public interface PcClassStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPcFilePath();

    /**
     * Returns PCClass data as a {@link ReadOnlyPcClass}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPcClass> readPC() throws DataConversionException, IOException;

    /**
     * @see #getPcFilePath()
     */
    Optional<ReadOnlyPcClass> readPC(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPcClass} to the storage.
     * @param readOnlyPcClass cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePC(ReadOnlyPcClass readOnlyPcClass) throws IOException;

    void savePC(ReadOnlyPcClass readOnlyPcClass, Path filePath) throws IOException;
}
