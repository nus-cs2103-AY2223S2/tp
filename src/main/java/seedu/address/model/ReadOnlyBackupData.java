package seedu.address.model;

import java.util.List;

import seedu.address.storage.JsonAdaptedBackup;

/**
 * Unmodifiable view of backup data.
 */
public interface ReadOnlyBackupData {

    List<JsonAdaptedBackup> getJsonAdaptedBackups();
}
