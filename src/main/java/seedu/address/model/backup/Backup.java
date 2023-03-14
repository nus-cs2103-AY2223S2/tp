package seedu.address.model.backup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Backup file.
 * Guarantees: immutable; is always valid
 */
public class Backup {

    public static final String INDEX_CONSTRAINTS = "Index of backup should be between 1 and 10";
    public final String backupLocation;
    public final LocalDateTime backupTime;
    private final Index index;

    /**
     * Constructs a {@code Backup}.
     *
     * @param index Index of the backup.
     */
    public Backup(Index index) {
        requireNonNull(index);
        checkArgument(isValidIndex(index), INDEX_CONSTRAINTS);
        this.index = index;
        this.backupLocation = getBackupLocation(index);
        this.backupTime = LocalDateTime.now();
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
        return this.index;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Backup // instanceof handles nulls
                && this.index.equals(((Backup) other).index)) // state check
                && Objects.equals(this.backupLocation, ((Backup) other).backupLocation);
    }
}
