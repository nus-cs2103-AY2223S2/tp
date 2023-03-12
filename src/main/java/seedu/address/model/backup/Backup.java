package seedu.address.model.backup;

import seedu.address.commons.core.index.Index;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Backup file.
 * Guarantees: immutable; is always valid
 */
public class Backup {

    public static final String INDEX_CONSTRAINTS = "Index of backup should be between 1 and 10";
    public final Index INDEX;
    public final String BACKUP_LOCATION;
    public final LocalDateTime BACKUP_TIME;

    /**
     * Constructs a {@code Backup}.
     *
     * @param index Index of the backup.
     */
    public Backup(Index index) {
        requireNonNull(index);
        checkArgument(isValidIndex(index), INDEX_CONSTRAINTS);
        this.INDEX = index;
        this.BACKUP_LOCATION = getBackupLocation(index);
        this.BACKUP_TIME = LocalDateTime.now();
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
        return "data/backup/addressbookBackup"
                + index.getOneBased()
                + ".json";
    }

    /**
     * Returns the index of a backup
     */
    public Index getBackupIndex() {
        return this.INDEX;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Backup // instanceof handles nulls
                && this.INDEX.equals(((Backup) other).INDEX)) // state check
                && Objects.equals(this.BACKUP_LOCATION, ((Backup) other).BACKUP_LOCATION);
    }
}
