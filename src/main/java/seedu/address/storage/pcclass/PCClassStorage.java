package seedu.address.storage.pcclass;

import seedu.address.commons.exceptions.DataConversionException;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.model.person.ReadOnlyPCClass;

import java.util.Optional;

public interface PCClassStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPCFilePath();

    /**
     * Returns PCClass data as a {@link ReadOnlyPCClass}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPCClass> readPC() throws DataConversionException, IOException;

    /**
     * @see #getPCFilePath()
     */
    Optional<ReadOnlyPCClass> readPC(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPCClass} to the storage.
     * @param teaPet cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePC(ReadOnlyPCClass teaPet) throws IOException;

    void savePC(ReadOnlyPCClass teaPet, Path filePath) throws IOException;
}
