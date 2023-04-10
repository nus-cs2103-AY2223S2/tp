
package seedu.address.ui.calendar;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import seedu.address.model.session.Session;
import seedu.address.ui.UiPart;

//@@author wongyewjon
/**
 * The Popup that is displayed when a {@code CalendarButton} is interacted with.
 */

public class CalendarPopup extends UiPart<Popup> {
    private static final String FXML = "CalendarPopup.fxml";
    private final Node owner;
    @FXML
    private Popup popup;

    //@@author wongyewjon
    /**
     * Creates a {@code CalendarPopup} with the given {@code CalendarEvent} details.
     * @param session The {@code CalendarEvent} to display in the popup.
     * @param owner The owner of the popup.
     */

    public CalendarPopup(Session session, Node owner) {
        super(FXML);
        this.owner = owner;
        initialiseCalendarPopup(session);
        popup.sizeToScene();
    }

    //@@author wongyewjon
    /**
     * Initializes the content of the {@code CalendarPopup} with the given {@code CalendarEvent}.
     * @param session The {@code CalendarEvent} to display in the popup.
     */

    private void initialiseCalendarPopup(Session session) {
        popup.getContent().add(new CalendarPopupContent(session).getRoot());
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
    }
}

