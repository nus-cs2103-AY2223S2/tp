package seedu.fitbook.ui;

import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Duration;
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
     * Creates a {@code ExercisePanel} with the given {@code ObservableList}.
     */
    public ExercisePanel(ObservableList<Routine> routineList) {
        super(FXML);
        exerciseView.setItems(routineList);
        exerciseView.setCellFactory(listView -> new ExercisePanel.RoutineListViewCell());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            exerciseView.refresh();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Routine} using a {@code ExerciseCard}.
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
