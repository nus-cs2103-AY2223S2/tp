package seedu.address.ui.exam;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Exam;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Homeworks.
 */
public class ExamsListPanel extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(ExamsListPanel.class);
    private static final String FXML = "ExamsListPanel.fxml";

    @FXML
    private ListView<Exam> examsListView;

    /**
     * Creates a {@code HomeworkListPanel} with the given {@code ObservableList}.
     *
     * @param examsList The list of exams to display.
     */
    public ExamsListPanel(ObservableList<Exam> examsList) {
        super(FXML);
        examsListView.setItems(examsList);
        examsListView.setCellFactory(listView -> new ExamListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Homework} using a {@code HomeworkCard}.
     */
    static class ExamListViewCell extends ListCell<Exam> {
        @Override
        protected void updateItem(Exam exam, boolean empty) {
            super.updateItem(exam, empty);

            if (empty || exam == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExamCard(exam, getIndex() + 1).getRoot());
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
        if (!(other instanceof ExamsListPanel)) {
            return false;
        }

        // state check
        ExamsListPanel otherExamsListPanel = (ExamsListPanel) other;
        return examsListView.equals(otherExamsListPanel.examsListView);
    }
}
