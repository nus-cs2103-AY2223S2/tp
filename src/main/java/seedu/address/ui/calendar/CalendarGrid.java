package seedu.address.ui.calendar;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import seedu.address.ui.UiPart;
//@@author wongyewjon
/**
 * A UI component that represents the grid of the Calendar.
 */
public class CalendarGrid extends UiPart<GridPane> {
    private static final String FXML = "CalendarGrid.fxml";
    @FXML
    private GridPane calendarGrid;
    //@@author wongyewjon
    public CalendarGrid() {
        super(FXML);
    }
}
