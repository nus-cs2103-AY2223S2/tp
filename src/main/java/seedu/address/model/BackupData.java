package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.backup.Backup;
import seedu.address.storage.JsonAdaptedBackup;

/**
 * Represents backup data.
 */
public class BackupData implements ReadOnlyBackupData {

    private List<JsonAdaptedBackup> backups = new ArrayList<>();

    /**
     * Creates a {@code BackupData} with default values.
     */
    public BackupData() {
    }

    /**
     * Creates a {@code BackupData} with the data in {@code newBackupData}.
     */
    public BackupData(ReadOnlyBackupData newBackupData) {
        this();
        resetData(newBackupData);
    }

    public List<JsonAdaptedBackup> getJsonAdaptedBackups() {
        return backups;
    }

    /**
     * Resets the existing data of this {@code BackupData} with {@code newBackupData}.
     */
    public void resetData(ReadOnlyBackupData newBackupData) {
        requireNonNull(newBackupData);
        setBackups(newBackupData.getJsonAdaptedBackups());
    }

    /**
     * Sets the backups of this {@code BackupData}
     */
    public void setBackups(List<JsonAdaptedBackup> backups) {
        this.backups = backups;
    }

    /**
     * Adds a backup to {@code BackupData}
     */
    public void addBackup(JsonAdaptedBackup backup) {
        backups.removeIf(b -> b.index.equals(backup.index));
        backups.add(backup);
    }

    /**
     * Gets a {@code JsonAdaptedBackup} from {@code BackupData}
     *
     * @param i Index of the backup
     */
    public JsonAdaptedBackup getBackup(String i) throws IndexOutOfBoundsException {
        for (JsonAdaptedBackup b : backups) {
            if (b.index.equals(i)) {
                return b;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public void deleteBackup(JsonAdaptedBackup b) {
        backups.remove(b);
    }

    /**
     * Gets a list of {@code Backup} from {@code BackupData}
     * The list is sorted according to the backup index.
     */
    public List<Backup> getRawBackups() throws IllegalValueException {
        List<Backup> rawBackups = new ArrayList<>();
        for (JsonAdaptedBackup jBackup : backups) {
            rawBackups.add(jBackup.toModelType());
        }
        rawBackups.sort(new Comparator<Backup>() {
            @Override
            public int compare(Backup o1, Backup o2) {
                return o1.getBackupIndex().getOneBased() - o2.getBackupIndex().getOneBased();
            }
        });

        return rawBackups;
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

        return backups.equals(o.getJsonAdaptedBackups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(backups);
    }

    @Override
    public String toString() {
        return backups.toString();
    }
}
