
package seedu.address.ui.calendar;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.ui.UiPart;


/**
 * The Popup that is displayed when a {@code CalendarButton} is interacted with.
 */

public class CalendarPopup extends UiPart<Popup> {
    private static final String FXML = "CalendarPopup.fxml";
    private final Node owner;
    @FXML
    private Popup popup;


/**
     * Creates a {@code CalendarPopup} with the given {@code CalendarEvent} details.
     * @param calendarEvent The {@code CalendarEvent} to display in the popup.
     * @param owner The owner of the popup.
     */

    public CalendarPopup(CalendarEvent calendarEvent, Node owner) {
        super(FXML);
        this.owner = owner;
        initialiseCalendarPopup(calendarEvent);
        popup.sizeToScene();
    }


/**
     * Initializes the content of the {@code CalendarPopup} with the given {@code CalendarEvent}.
     * @param calendarEvent The {@code CalendarEvent} to display in the popup.
     */

    private void initialiseCalendarPopup(CalendarEvent calendarEvent) {
        popup.getContent().add(new CalendarPopupContent(calendarEvent).getRoot());
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
    }
}

