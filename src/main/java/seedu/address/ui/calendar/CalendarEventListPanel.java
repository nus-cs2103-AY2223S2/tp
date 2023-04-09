
package seedu.address.ui.calendar;

import static javafx.scene.paint.Color.WHITE;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.model.session.Session;
import seedu.address.ui.UiPart;


/**
 * Panel containing the list of CalendarEvents for a specific day in the calendar month.
 * This UI component displays the events in a vertical list, where each event is represented by an {@code EventButton}.
 * It takes in an {@code ObservableList} of {@code CalendarEvent} objects and a {@code Stage} object as input.
 * @see EventButton
 */

public class CalendarEventListPanel extends UiPart<Region> {
    private static final String FXML = "CalendarEventListPanel.fxml";
    private ObservableList<Session> calendarDayEvents;
    private Stage primaryStage;

    @FXML
    private VBox calendarEventList;


    /**
     * Constructs a new {@code CalendarEventListPanel} object with the specified list of {@code CalendarEvent} objects
     * and primary stage of the application.
     * @param calendarDayEvents The list of {@code CalendarEvent} objects to be displayed in the panel.
     * @param primaryStage The primary stage of the application.
     */
    public CalendarEventListPanel(ObservableList<Session> calendarDayEvents,
                                  Stage primaryStage) {
        super(FXML);
        this.calendarDayEvents = calendarDayEvents;
        this.calendarEventList = new VBox();
        this.primaryStage = primaryStage;

    }


    /**
     * Returns a {@code VBox} object containing the list of {@code CalendarEvent} objects for the specified day.
     * The list is displayed in a vertical list, where each event is represented by an {@code EventButton}.
     * @param currentDay The current day of the month.
     * @return A {@code VBox} object containing the list of {@code CalendarEvent} objects for the specified day.
     */

    public VBox getCalendarEventList(int currentDay) {
        Text tDate = getTextDate(currentDay);
        calendarEventList.getChildren().add(tDate);
        calendarDayEvents.stream().sorted()
                .forEach(x -> calendarEventList.getChildren()
                        .add(new EventButton(x, primaryStage).getRoot()));
        return calendarEventList;
    }


    /**
     * Returns a {@code Text} object displaying the current day of the month.
     * @param currentDay The current day of the month.
     * @return A {@code Text} object displaying the current day of the month.
     */

    private Text getTextDate(int currentDay) {
        Text tDate = new Text(String.valueOf(currentDay));
        tDate.setFill(WHITE);
        return tDate;
    }

}

