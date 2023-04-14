package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.backup.Backup;

public class JsonAdaptedBackupTest {
    private static final String INVALID_INDEX = "12";

    private static final String VALID_INDEX = "3";
    private static final String VALID_DESC = "Backup";
    private static final String VALID_BACKUP_TIME = "2023-03-27T20:05:17.428381800";

    @Test
    public void toModelType_validBackupDetails_returnsBackup() throws Exception {
        Backup backup = new Backup(Index.fromOneBased(Integer.parseInt(VALID_INDEX)), VALID_DESC, VALID_BACKUP_TIME);
        JsonAdaptedBackup jsonBackup = new JsonAdaptedBackup(VALID_INDEX, VALID_DESC, VALID_BACKUP_TIME);
        assertEquals(backup, jsonBackup.toModelType());
    }

    @Test
    public void toModelType_invalidIndex_throwsIllegalValueException() {
        JsonAdaptedBackup jsonBackup = new JsonAdaptedBackup(INVALID_INDEX, VALID_DESC, VALID_BACKUP_TIME);
        assertThrows(IllegalValueException.class, Backup.INDEX_CONSTRAINTS, jsonBackup::toModelType);
    }
}
