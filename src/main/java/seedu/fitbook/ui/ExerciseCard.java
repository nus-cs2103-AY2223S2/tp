package seedu.fitbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.fitbook.model.routines.Routine;

/**
 * A UI component that displays information of a {@code Routine}.
 */
public class ExerciseCard extends UiPart<Region> {
    private static final String FXML = "ExerciseCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitBook level 4</a>
     */

    public final Routine routine;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label exercises;
    @FXML
    private Label id;
    @FXML
    private Label routineName;

    /**
     * Creates a {@code RoutineCode} with the given {@code Rountine} and index to display.
     */
    public ExerciseCard(Routine routine, int displayedIndex) {
        super(FXML);
        this.routine = routine;
        id.setText(displayedIndex + ". ");
        routineName.setText(routine.getRoutineName().routineName);
        exercises.setText(routine.exerciseListToString().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExerciseCard)) {
            return false;
        }

        // state check
        ExerciseCard card = (ExerciseCard) other;
        return id.getText().equals(card.id.getText())
                && routine.equals(card.routine);
    }
}
