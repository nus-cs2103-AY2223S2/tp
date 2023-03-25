package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.backup.Backup;

/**
 * An UI component that displays information of a {@code Backup}.
 */
public class BackupCard extends UiPart<Region> {

    private static final String FXML = "BackupCard.fxml";

    private final String SLOT = "Save slot: ";

    private final String DESC = "Description: ";

    private final String TIME = "Time created: ";

    public final Backup backup;

    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label description;
    @FXML
    private Label backupTime;

    /**
     * Creates a {@code BackupCard} with the given {@code Backup}
     */
    public BackupCard(Backup backup) {
        super(FXML);
        this.backup = backup;
        index.setText(SLOT + String.valueOf(backup.getBackupIndex().getOneBased()));
        description.setText(DESC + backup.getBackupDesc());
        backupTime.setText(TIME + backup.backupTimeToString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BackupCard)) {
            return false;
        }

        BackupCard card = (BackupCard) other;
        return backup.equals(card.backup);
    }
}
