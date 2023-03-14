package seedu.address.ui.lesson;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Lesson;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Homeworks.
 */
public class LessonsListPanel extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(LessonsListPanel.class);
    private static final String FXML = "LessonsListPanel.fxml";

    @FXML
    private ListView<Lesson> lessonsListView;

    /**
     * Creates a {@code HomeworkListPanel} with the given {@code ObservableList}.
     */
    public LessonsListPanel(ObservableList<Lesson> lessonsList) {
        super(FXML);
        lessonsListView.setItems(lessonsList);
        lessonsListView.setCellFactory(listView -> new LessonsListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Homework} using a {@code HomeworkCard}.
     */
    static class LessonsListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }
}
