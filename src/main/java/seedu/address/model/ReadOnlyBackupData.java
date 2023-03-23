package seedu.address.model;

import seedu.address.model.backup.Backup;

import java.util.List;

/**
 * Unmodifiable view of backup data.
 */
public interface ReadOnlyBackupData {

    List<Backup> getBackups();
}
