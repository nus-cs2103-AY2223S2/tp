package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.backup.Backup;


/**
 * Jackson-friendly version of {@link Backup}.
 */
public class JsonAdaptedBackup {

    public final String index;
    public final String description;
    public final String backupTime;


    /**
     * Constructs a {@code JsonAdaptedBackup} with the given Backup details.
     */
    @JsonCreator
    public JsonAdaptedBackup(@JsonProperty("index") String index,
                             @JsonProperty("description") String description,
                             @JsonProperty("backupTime") String backupTime) {
        this.index = index;
        this.description = description;
        this.backupTime = backupTime;
    }

    /**
     * Converts a given {@code Backup} into this class for Jackson use.
     */
    public JsonAdaptedBackup(Backup source) {
        index = Integer.toString(source.getBackupIndex().getOneBased());
        description = source.getBackupDesc();
        backupTime = source.getBackupTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted backup object into the model's {@code Backup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted backup.
     */
    public Backup toModelType() throws IllegalValueException {
        Index thisIndex = Index.fromOneBased(Integer.parseInt(index));
        if (!Backup.isValidIndex(thisIndex)) {
            throw new IllegalValueException(Backup.INDEX_CONSTRAINTS);
        }
        return new Backup(thisIndex, description, backupTime);
    }
}
