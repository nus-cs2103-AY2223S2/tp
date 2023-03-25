package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BackupData;
import seedu.address.model.ReadOnlyBackupData;

/**
 * Represents a storage for {@link seedu.address.model.BackupData}.
 */
public interface BackupDataStorage {

    /**
     * Returns the file path of the BackupData data file.
     */
    Path getBackupDataFilePath();

    /**
     * Returns BackupData data from storage
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if data in storage is not in the expected format
     * @throws IOException             if there was any problem when reading from storage
     */
    Optional<BackupData> readBackupData() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyBackupData} to the storage.
     *
     * @param backupData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBackupData(ReadOnlyBackupData backupData) throws IOException;
}
