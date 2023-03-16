package seedu.address.ui.homework;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Homework;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Homeworks.
 */
public class HomeworkListPanel extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(HomeworkListPanel.class);
    private static final String FXML = "HomeworkListPanel.fxml";

    @FXML
    private ListView<Homework> homeworkListView;

    /**
     * Creates a {@code HomeworkListPanel} with the given {@code ObservableList}.
     */
    public HomeworkListPanel(ObservableList<Homework> homeworkList) {
        super(FXML);
        homeworkListView.setItems(homeworkList);
        homeworkListView.setCellFactory(listView -> new HomeworkListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Homework} using a {@code HomeworkCard}.
     */
    static class HomeworkListViewCell extends ListCell<Homework> {
        @Override
        protected void updateItem(Homework homework, boolean empty) {
            super.updateItem(homework, empty);

            if (empty || homework == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HomeworkCard(homework, getIndex() + 1).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HomeworkListPanel)) {
            return false;
        }

        // state check
        HomeworkListPanel panel = (HomeworkListPanel) other;
        return homeworkListView.equals(panel.homeworkListView);
    }
}
