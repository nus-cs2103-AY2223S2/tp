package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.backup.Backup;

/**
 * Panel containing the list of backups.
 */
public class BackupListPanel extends UiPart<Region> {
    private static final String FXML = "BackupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BackupListPanel.class);

    @FXML
    private ListView<Backup> backupListView;

    /**
     * Creates a {@code BackupListPanel} with the given {@code ObservableList}.
     */
    public BackupListPanel(ObservableList<Backup> backupList) {
        super(FXML);
        backupListView.setItems(backupList);
        backupListView.setCellFactory(listView -> new BackupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Backup} using a {@code BackupCard}.
     */
    class BackupListViewCell extends ListCell<Backup> {
        @Override
        protected void updateItem(Backup backup, boolean empty) {
            super.updateItem(backup, empty);

            if (empty || backup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BackupCard(backup).getRoot());
            }
        }
    }
}
