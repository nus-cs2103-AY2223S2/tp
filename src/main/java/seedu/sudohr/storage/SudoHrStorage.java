package seedu.sudohr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.sudohr.commons.exceptions.DataConversionException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.SudoHr;

/**
 * Represents a storage for {@link SudoHr}.
 */
public interface SudoHrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSudoHrFilePath();

    /**
     * Returns SudoHr data as a {@link ReadOnlySudoHr}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySudoHr> readSudoHr() throws DataConversionException, IOException;

    /**
     * @see #getSudoHrFilePath()
     */
    Optional<ReadOnlySudoHr> readSudoHr(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySudoHr} to the storage.
     * @param sudoHr cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSudoHr(ReadOnlySudoHr sudoHr) throws IOException;

    /**
     * @see #saveSudoHr(ReadOnlySudoHr)
     */
    void saveSudoHr(ReadOnlySudoHr sudoHr, Path filePath) throws IOException;

}
