package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.BackupData;
import seedu.address.model.backup.Backup;
import seedu.address.storage.JsonAdaptedBackup;

/**
 * A utility class containing a list of {@code Backup} objects to be used in tests.
 */
public class TypicalBackups {
    public static final Backup BACKUP1 = new Backup(Index.fromOneBased(1), "Backup 1");
    public static final Backup BACKUP2 = new Backup(Index.fromOneBased(2), "Backup 2");
    public static final Backup BACKUP3 = new Backup(Index.fromOneBased(3), "Backup 3");

    public static BackupData getTypicalBackData() {
        BackupData bd = new BackupData();
        for (Backup backup : getTypicalBackups()) {
            bd.addBackup(new JsonAdaptedBackup(backup));
        }
        return bd;
    }

    public static List<Backup> getTypicalBackups() {
        return new ArrayList<>(Arrays.asList(BACKUP1, BACKUP2, BACKUP3));
    }

}
