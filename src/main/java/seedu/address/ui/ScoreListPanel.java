package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.score.Score;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of scores.
 */

public class ScoreListPanel extends UiPart<Region> {
    private static final String FXML = "ScoreListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScoreListPanel.class);

    @FXML
    private ListView<Score> scoreListView;

    /**
     * Creates a {@code ScoreListPanel} with the given {@code ObservableList}.
     */
    public ScoreListPanel() {
        super(FXML);
        // scoreListView.setItems(taskList);
        // scoreListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Score} using a {@code ScoreCard}.
     */
    class ScoreListViewCell extends ListCell<Score> {
        @Override
        protected void updateItem(Score score, boolean empty) {
            super.updateItem(score, empty);

            if (empty || score == null) {
                setGraphic(null);
                setText(null);
            } else {
                // setGraphic(new ScoreCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}
