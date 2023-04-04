package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    private Logic logic;

    private ExportProgressWindow exportProgressWindow;

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        studentListView.setItems(logic.getFilteredStudentList());
        studentListView.setCellFactory(listView -> new StudentListViewCell());
        this.exportProgressWindow = new ExportProgressWindow(null, this.logic);
    }

    public void hideExportProgressWindow() {
        this.exportProgressWindow.hide();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1, exportProgressWindow).getRoot());
            }
        }
    }
}
