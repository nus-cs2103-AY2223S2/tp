package seedu.address.model;

import seedu.address.storage.JsonAdaptedBackup;

import java.util.List;

/**
 * Unmodifiable view of backup data.
 */
public interface ReadOnlyBackupData {

    List<JsonAdaptedBackup> getJsonAdaptedBackups();
}
