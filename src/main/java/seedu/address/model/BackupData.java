package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.backup.Backup;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents backup data.
 */
public class BackupData implements ReadOnlyBackupData {

    private List<Backup> backups = new ArrayList<>();

    public List<Backup> getBackups() {
        return backups;
    }

    public BackupData() {
    }

    public BackupData(ReadOnlyBackupData newBackupData) {
        this();
        resetData(newBackupData);
    }

    public void resetData(ReadOnlyBackupData newBackupData) {
        requireNonNull(newBackupData);
        setBackups(newBackupData.getBackups());
    }

    public void setBackups(List<Backup> backups) {
        this.backups = backups;
    }

    public void addBackup(Backup b) {
        backups.add(b);
    }

    public void deleteBackup(Backup b) {
        backups.remove(b);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BackupData)) {
            return false;
        }

        BackupData o = (BackupData) other;

        return backups.equals(o.getBackups());
    }

    @Override
    public String toString() {
        return backups.toString();
    }
}
