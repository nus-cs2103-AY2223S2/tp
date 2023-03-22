package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.lecture.ReadOnlyLecture;

/**
 * Panel containing the list of modules.
 */
public class LectureListPanel extends UiPart<Region> {
    private static final String FXML = "LectureListPanel.fxml";

    @FXML
    private ListView<ReadOnlyLecture> lectureListView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public LectureListPanel(ObservableList<ReadOnlyLecture> observableList) {
        super(FXML);
        lectureListView.setItems(observableList);
        lectureListView.setCellFactory(listView -> new LectureListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class LectureListViewCell extends ListCell<ReadOnlyLecture> {
        @Override
        protected void updateItem(ReadOnlyLecture module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LectureCard(module, getIndex() + 1).getRoot());
            }
        }
    }

}
