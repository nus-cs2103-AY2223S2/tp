package seedu.fitbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.model.routines.Routine;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Exercise} using a {@code ExerciseCard}.
 */
public class ExercisePanel extends UiPart<Region> {
    private static final String FXML = "ExercisePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExercisePanel.class);

    @javafx.fxml.FXML
    private ListView<Routine> exerciseView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ExercisePanel(ObservableList<Routine> routineList) {
        super(FXML);
        exerciseView.setItems(routineList);
        exerciseView.setCellFactory(listView -> new ExercisePanel.RoutineListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class RoutineListViewCell extends ListCell<Routine> {
        @Override
        protected void updateItem(Routine routine, boolean empty) {
            super.updateItem(routine, empty);

            if (empty || routine == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExerciseCard(routine, getIndex() + 1).getRoot());
            }
        }
    }
}
