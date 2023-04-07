package seedu.address.model.backup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Backup file.
 * Guarantees: immutable; is always valid
 */
public class Backup {

    public static final String INDEX_CONSTRAINTS = "Index of backup should be between 1 and 10";
    public final String backupLocation;
    public final String description;
    public final LocalDateTime backupTime;
    private final Index index;

    /**
     * Constructs a {@code Backup}.
     *
     * @param index       Index of the backup.
     * @param description Description of the backup.
     */
    public Backup(Index index, String description) {
        requireNonNull(index);
        checkArgument(isValidIndex(index), INDEX_CONSTRAINTS);
        this.index = index;
        this.description = description;
        this.backupLocation = getBackupLocation(index);
        this.backupTime = LocalDateTime.now();
    }

    /**
     * Constructs a {@code Backup}.
     *
     * @param index       Index of the backup.
     * @param description Description of the backup.
     * @param backupTime  Timestamp of the backup.
     */
    public Backup(Index index, String description, String backupTime) {
        requireNonNull(index);
        checkArgument(isValidIndex(index), INDEX_CONSTRAINTS);
        this.index = index;
        this.description = description;
        this.backupLocation = getBackupLocation(index);
        this.backupTime = LocalDateTime.parse(backupTime);
    }

    /**
     * Returns true if a given index is a valid
     */
    public static boolean isValidIndex(Index test) {
        return test.getOneBased() <= 10 && test.getOneBased() > 0;
    }

    /**
     * Returns the location of the backup
     *
     * @param index Index of backup
     */
    public String getBackupLocation(Index index) {
        return "data/backup/hospirecordBackup"
                   + index.getOneBased()
                   + ".json";
    }

    /**
     * Returns the description of the backup
     */
    public String getBackupDesc() {
        return this.description;
    }

    /**
     * Returns the index of a backup
     */
    public Index getBackupIndex() {
        return this.index;
    }

    /**
     * Returns the timestamp of a backup
     */
    public LocalDateTime getBackupTime() {
        return this.backupTime;
    }

    /**
     * Returns the timestamp of a backup as a string
     */
    public String backupTimeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.backupTime.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof Backup // instanceof handles nulls
                           && this.index.equals(((Backup) other).index)) // state check
                          && Objects.equals(this.backupLocation, ((Backup) other).backupLocation);
    }
}
