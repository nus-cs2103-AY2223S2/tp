
package seedu.address.ui.calendar;


import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seedu.address.logic.CalendarLogic;

//@@author wongyewjon
/**
 * A button that displays the next month's CalendarEvents when interacted with.
 * The button extends {@code CalendarButton} which contains the common behaviour
 * of all buttons in the calendar.
 */

public class NextButton extends CalendarButton {
    private static final String FXML = "NextButton.fxml";
    private CalendarLogic calendarLogic;
    @FXML
    private Button nextButton;

    //@@author wongyewjon
    /**
     * Constructs a new {@code NextButton} with the given content and {@code CalendarLogic}.
     * @param content the text displayed on the button
     * @param calendarLogic the logic that manages the calendar
     */

    public NextButton(String content, CalendarLogic calendarLogic) {
        super(FXML);
        this.calendarLogic = calendarLogic;
        nextButton.setText(content);
        nextButton.focusedProperty().addListener(this::handleFocusedEvent);
    }

    //@@author wongyewjon
    /**
     * Handles the button's focused event.
     * When the button is focused, it changes its border color to orange,
     * otherwise to grey.
     * @param observable an object representing the button's focus state
     */

    @FXML
    protected void handleFocusedEvent(Observable observable) {
        if (nextButton.isFocused()) {
            nextButton.setStyle(CALENDAR_BUTTON_STYLE + ORANGE_BORDER);
        } else {
            nextButton.setStyle(CALENDAR_BUTTON_STYLE + GREY_BORDER);
        }
    }

    //@@author wongyewjon
    /**
     * Handles the button's onAction event.
     * When the button is clicked, it moves the calendar to the next month.
     * @param event the event that triggered the method call
     */

    @FXML
    protected void handleOnAction(ActionEvent event) {
        calendarLogic.next();
    }
}

