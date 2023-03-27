package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.BackupData;
import seedu.address.model.ReadOnlyBackupData;

/**
 * A class to access Backupdata stored in the hard disk as a json file
 */
public class JsonBackupDataStorage implements BackupDataStorage {

    private Path filePath;

    /**
     * Creates a JsonBackupDataStorage object.
     */
    public JsonBackupDataStorage(Path filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public Path getBackupDataFilePath() {
        return filePath;
    }

    @Override
    public Optional<BackupData> readBackupData() throws DataConversionException {
        return readBackupData(filePath);
    }

    public Optional<BackupData> readBackupData(Path backupDataFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(backupDataFilePath, BackupData.class);
    }

    @Override
    public void saveBackupData(ReadOnlyBackupData backupData) throws IOException {
        JsonUtil.saveJsonFile(backupData, filePath);
    }
}
